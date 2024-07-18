package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.enumm.MissionState;
import mas.apazniak.mas_final_s22326.model.*;
import mas.apazniak.mas_final_s22326.model.DTO.MissionDetailsDTO;
import mas.apazniak.mas_final_s22326.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionRepository missionRepository;
    private final LocationRepository locationRep;
    private final FieldTeamRepository fieldTeamRep;
    private final ExplorationVehicleRepository explorationVehicleRep;

    private final IntermediateLocationRepository intermediateLocationRep;

    @Autowired
    public MissionController(MissionRepository missionRepository, LocationRepository locationRep,
                             FieldTeamRepository fieldTeamRep, ExplorationVehicleRepository explorationVehicleRep,
                             IntermediateLocationRepository intermediateLocationRep)
    {
        this.missionRepository = missionRepository;
        this.locationRep = locationRep;
        this.fieldTeamRep = fieldTeamRep;
        this.explorationVehicleRep = explorationVehicleRep;
        this.intermediateLocationRep = intermediateLocationRep;
    }

    @GetMapping("/current")
    public List<Mission> getCurrentMissions() {
        List<Mission> currentMissions = missionRepository.findByStateNotIn(MissionState.ARCHIVED);

        return currentMissions;
    }

    @GetMapping("/archived")
    public List<Mission> getArchivedMissions() {
        List<Mission> currentMissions = missionRepository.findByStateIn(MissionState.ARCHIVED);

        return currentMissions;
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<MissionDetailsDTO> findById(@PathVariable String id) {
        Optional<Mission> missionOptional = missionRepository.findById(Long.parseLong(id));
        try {
            Mission mission = missionOptional.orElseThrow(() ->
                    new RuntimeException("Mission not found."));

            MissionDetailsDTO missionDetailsDTO = missionConvertToItem(mission);

            return ResponseEntity.ok(missionDetailsDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private static MissionDetailsDTO missionConvertToItem(Mission mission) {
        return MissionDetailsDTO.builder()
                .id(mission.getId())
                .type(mission.getType())
                .startLocation(mission.getStartLocation())
                .intermediateLocations(mission.getIntermediateLocations())
                .endLocation(mission.getEndLocation())
                .fieldTeam(mission.getFieldTeam())
                .explorationVehicle(mission.getExplorationVehicle())
                .state(mission.getState())
                .startDate(mission.getStartDate())
                .endDate(mission.getEndDate())
                .build();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMission(@RequestBody Map<String, Object> missionData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate startDate = LocalDate.parse((String) missionData.get("startDate"), formatter);
            LocalDate endDate = LocalDate.parse((String) missionData.get("endDate"), formatter);
            String type = (String) missionData.get("type");
            String fieldTeam = (String) missionData.get("selectedFieldTeam");
            String explorationVehicle = (String) missionData.get("selectedExplorationVehicle");
            String startLocation = (String) missionData.get("startLocation");
            String endLocation = (String) missionData.get("endLocation");
            Boolean includeIntermediateLocations = (Boolean) missionData.get("includeIntermediateLocations");
            List<Integer> intermediateLocationsList = (List<Integer>) missionData.get("intermediateLocations");
            Set<Location> intermediateLocationsListClasses = new HashSet<>();

            Optional<Location> startLocationOpt = locationRep.findById(Long.parseLong(startLocation));
            Optional<Location> endLocationOpt = locationRep.findById(Long.parseLong(endLocation));
            Optional<FieldTeam> fieldTeamOpt = fieldTeamRep.findById(Long.parseLong(fieldTeam));
            Optional<ExplorationVehicle> explorationVehicleOpt = explorationVehicleRep.findById(Long.parseLong(explorationVehicle));

            Location startLocationsClass = startLocationOpt.orElseThrow(() ->
                    new RuntimeException("Start Location is missing."));

            Location endLocationClass = endLocationOpt.orElseThrow(() ->
                    new RuntimeException("End Location is missing."));

            FieldTeam fieldTeamClass = fieldTeamOpt.orElseThrow(() ->
                    new RuntimeException("Field Team is missing."));

            ExplorationVehicle explorationVehicleClass = explorationVehicleOpt.orElseThrow(() ->
                    new RuntimeException("Exploration Vehicle is missing."));

            Mission.MissionBuilder missionBuilder = Mission.builder()
                    .fieldTeam(fieldTeamClass)
                    .explorationVehicle(explorationVehicleClass)
                    .startDate(startDate)
                    .endDate(endDate)
                    .type(type)
                    .startLocation(startLocationsClass)
                    .endLocation(endLocationClass)
                    .state(MissionState.SCHEDULED);

            Mission mission = missionBuilder.build();
            mission.updateStateBasedOnStartDate();
            missionRepository.save(mission);

            if (includeIntermediateLocations)  {
                List<IntermediateLocation> intermediateLocations = getIntermediateLocations(intermediateLocationsList, mission);

                if (!intermediateLocationsListClasses.isEmpty()) {
                    missionBuilder.intermediateLocations(intermediateLocations);
                    missionRepository.save(mission);
                }
            }
            return ResponseEntity.ok("Mission created successfully");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create mission");
        }
    }

    private List<IntermediateLocation> getIntermediateLocations(List<Integer> intermediateLocationsList, Mission mission) {
        List<IntermediateLocation> intermediateLocations = new ArrayList<>();

        if (intermediateLocationsList != null) {
            int sequence = 1;
            for (Integer locationId : intermediateLocationsList) {
                Optional<Location> locationOpt = locationRep.findById(Long.parseLong(locationId.toString()));

                Location location = locationOpt.orElseThrow(() ->
                        new RuntimeException("Intermediate Location is missing for location ID: " + locationId));

                IntermediateLocation intermediateLocation = IntermediateLocation.builder()
                        .mission(mission)
                        .location(location)
                        .visitOrder(sequence++)
                        .build();

                intermediateLocations.add(intermediateLocation);
            }
        }

        intermediateLocationRep.saveAll(intermediateLocations);

        return intermediateLocations;
    }
}

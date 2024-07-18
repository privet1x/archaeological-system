package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.model.ArchaeologicalEmployee;
import mas.apazniak.mas_final_s22326.model.FieldTeam;
import mas.apazniak.mas_final_s22326.model.DTO.FieldTeamDTO;
import mas.apazniak.mas_final_s22326.model.Mission;

import mas.apazniak.mas_final_s22326.repository.FieldTeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fieldTeams")
public class FieldTeamController {

    private final FieldTeamRepository fieldTeamRepository;

    public FieldTeamController(FieldTeamRepository fieldTeamRepository) {
        this.fieldTeamRepository = fieldTeamRepository;
    }

    @GetMapping
    public List<FieldTeam> list() {
        List<FieldTeam> fieldTeams= (List<FieldTeam>) fieldTeamRepository.findAll();

        return fieldTeams;
    }

    @GetMapping("/byId/{fieldTeamId}")
    public ResponseEntity<FieldTeam> getFieldTeamById(@PathVariable Long fieldTeamId) {
        Optional<FieldTeam> fieldTeamOptional = fieldTeamRepository.findById(fieldTeamId);
        if (fieldTeamOptional.isPresent()) {
            return new ResponseEntity<>(fieldTeamOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmployeesById/{fieldTeamId}")
    public ResponseEntity<List<FieldTeamDTO>> getEmployeesById(@PathVariable Long fieldTeamId) {
        Optional<FieldTeam> fieldTeamOptional = fieldTeamRepository.findById(fieldTeamId);
        if (fieldTeamOptional.isPresent()) {

            List<FieldTeamDTO> fieldTeamDtoList = new ArrayList<>();
            for (ArchaeologicalEmployee item : fieldTeamOptional.get().getArchaeologicalEmployees()) {
                FieldTeamDTO FieldTeamDTO = fieldConvertToItem(item);
                fieldTeamDtoList.add(FieldTeamDTO);
            }

            return new ResponseEntity<>(fieldTeamDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private static FieldTeamDTO fieldConvertToItem(ArchaeologicalEmployee archaeologicalEmployee){
        return FieldTeamDTO.builder()
                .id(archaeologicalEmployee.getId())
                .responsibilities(archaeologicalEmployee.getResponsibilities())
                .fieldTeamRole(archaeologicalEmployee.getFieldTeamRole())
                .firstName(archaeologicalEmployee.getFirstName())
                .lastName(archaeologicalEmployee.getLastName())
                .nationality(archaeologicalEmployee.getNationality())
                .employments(archaeologicalEmployee.getEmployments())
                .fieldTeam(archaeologicalEmployee.getFieldTeam())
                .build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<FieldTeam>> getAvailableFieldTeam() {
        LocalDate today = LocalDate.now();
        List<FieldTeam> availableFieldTeam = new ArrayList<>();
        List<FieldTeam> allFieldTeams = fieldTeamRepository.findAll();

        for (FieldTeam fieldTeam : allFieldTeams) {
            boolean isAvailable = true;

            List<Mission> missions = fieldTeam.getMissions();
            if (missions != null) {
                for (Mission mission : missions) {
                    LocalDate startDate = mission.getStartDate();
                    LocalDate endDate = mission.getEndDate();

                    if (today.isAfter(startDate) && today.isBefore(endDate)) {
                        isAvailable = false;
                        break;
                    }
                }
            }

            if (isAvailable) {
                availableFieldTeam.add(fieldTeam);
            }
        }

        return ResponseEntity.ok(availableFieldTeam);
    }

}


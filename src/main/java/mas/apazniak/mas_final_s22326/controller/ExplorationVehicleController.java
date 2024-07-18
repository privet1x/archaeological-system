package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.model.DTO.ExplorationVehicleDTO;
import mas.apazniak.mas_final_s22326.model.ExplorationVehicle;
import mas.apazniak.mas_final_s22326.repository.ExplorationVehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/explorationVehicles")
public class ExplorationVehicleController {

    private final ExplorationVehicleRepository explorationVehicleRepository;

    public ExplorationVehicleController(ExplorationVehicleRepository explorationVehicleRepository) {
        this.explorationVehicleRepository = explorationVehicleRepository;
    }

    @GetMapping("byCompany/{companyId}")
    public List<ExplorationVehicle> listByCompany(@PathVariable Long companyId) {
        return explorationVehicleRepository.findByResearchCompany_Id(companyId);
    }

    @GetMapping("byId/{vehicleId}")
    public ResponseEntity<ExplorationVehicleDTO> getExplorationVehicleById(@PathVariable Long vehicleId) {
        Optional<ExplorationVehicle> explorationVehicleOptional = explorationVehicleRepository.findById(vehicleId);
        if (explorationVehicleOptional.isPresent()) {
            ExplorationVehicleDTO dto = vehicleConvertToItem(explorationVehicleOptional.get());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    private static ExplorationVehicleDTO vehicleConvertToItem(ExplorationVehicle explorationVehicle) {
        return ExplorationVehicleDTO.builder()
                .id(explorationVehicle.getId())
                .name(explorationVehicle.getName())
                .type(explorationVehicle.getType())
                .maxSpeed(explorationVehicle.getMaxSpeed())
                .productionYear(explorationVehicle.getProductionYear())
                .build();
    }

    @GetMapping
    public List<ExplorationVehicle> list() {
        List<ExplorationVehicle> explorationVehicles= (List<ExplorationVehicle>) explorationVehicleRepository.findAll();

        return explorationVehicles;
    }
}

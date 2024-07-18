package mas.apazniak.mas_final_s22326.model.DTO;

import lombok.*;
import mas.apazniak.mas_final_s22326.enumm.MissionState;
import mas.apazniak.mas_final_s22326.model.ExplorationVehicle;
import mas.apazniak.mas_final_s22326.model.FieldTeam;
import mas.apazniak.mas_final_s22326.model.IntermediateLocation;
import mas.apazniak.mas_final_s22326.model.Location;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionDetailsDTO {
    private Long id;
    private String type;
    private Location startLocation;
    private List<IntermediateLocation> intermediateLocations;
    private Location endLocation;
    private FieldTeam fieldTeam;
    private ExplorationVehicle explorationVehicle;
    private MissionState state;
    private LocalDate startDate;
    private LocalDate endDate;
}

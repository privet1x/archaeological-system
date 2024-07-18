package mas.apazniak.mas_final_s22326.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ExplorationVehicleDTO {

    private Long id;
    private String name;
    private String type;
    private int maxSpeed;
    private LocalDate productionYear;
}

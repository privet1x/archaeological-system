package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import mas.apazniak.mas_final_s22326.model.Converter.StringSetConverter;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Compartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String supplierCompany;

    @Positive
    private int capacity;

    @Convert(converter = StringSetConverter.class)
    private Set<String> suitableFor;

    @ManyToOne
    @JoinColumn(name = "exploration_vehicle_id")
    @JsonIgnore
    private ExplorationVehicle explorationVehicle;


}

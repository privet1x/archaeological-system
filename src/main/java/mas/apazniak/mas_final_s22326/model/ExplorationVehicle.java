package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExplorationVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private ResearchCompany researchCompany;

    @OneToMany(mappedBy = "explorationVehicle")
    @JsonIgnore
    private List<Compartment> compartments;

    @NotBlank(message="Name is mandatory.")
    @Size(min = 2, max = 255)
    private String name;

    @Getter
    @OneToMany(mappedBy = "explorationVehicle", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Mission> missions;

    @NotBlank(message="Type is mandatory.")
    @Size(min = 2, max = 255)
    private String type;

    @Positive
    private int maxSpeed;
    @PastOrPresent
    private LocalDate productionYear;

    public String getName() {
        return name;
    }


}


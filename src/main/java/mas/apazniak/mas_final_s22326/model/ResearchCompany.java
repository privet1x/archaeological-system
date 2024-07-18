package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ResearchCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @NotBlank
    private String contactInfo;

    @NotBlank
    private String specializationArea;

    @Positive
    private Integer empSize;

    @OneToMany(mappedBy = "researchCompany")
    @JsonIgnore
    private List<Employment> employments;

    @OneToMany(mappedBy = "researchCompany")
    @JsonIgnore
    private List<ExplorationVehicle> explorationVehicles;

    @OneToMany(mappedBy = "researchCompany")
    @JsonIgnore
    private List<FieldTeam> fieldTeams;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @NotBlank(message="Name is mandatory.")
    @Size(min = 2, max = 255)
    private String name;
}

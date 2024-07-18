package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class FieldTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private ResearchCompany researchCompany;

    @OneToMany(mappedBy = "fieldTeam")
    @JsonIgnore
    private List<ArchaeologicalEmployee> archaeologicalEmployees;

    @OneToMany(mappedBy = "fieldTeam", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Mission> missions;

    @NotBlank(message="Name is mandatory.")
    @Size(min = 2, max = 255)
    private String name;

    @Size(min = 2, max = 255)
    private String description;

    public List<Mission> getMissions() {
        return missions;
    }

    public List<ArchaeologicalEmployee> getTeamMembers() {
        return archaeologicalEmployees;
    }
}


package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mas.apazniak.mas_final_s22326.enumm.MissionState;
import mas.apazniak.mas_final_s22326.model.Converter.StringSetConverter;
import mas.apazniak.mas_final_s22326.model.interfaces.IAdministrator;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Employee extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ElementCollection(targetClass = EmployeeType.class)
//    @Enumerated(EnumType.STRING)
//    private EnumSet<EmployeeType> empType;


    @Getter
    @Convert(converter = StringSetConverter.class)
    private Set<String> responsibilities;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Employment> employments;

    public Employee(String firstName, String lastName, String nationality, LocalDate dob, Set<String> responsibilities) {
        super(firstName, lastName, nationality, dob);
        this.responsibilities = responsibilities;
    }

    public Mission getCurrentMission() {
        for (Employment employment : employments) {
            ResearchCompany company = employment.getResearchCompany();
            for (ExplorationVehicle vehicle : company.getExplorationVehicles()) {
                for (Mission mission : vehicle.getMissions()) {
                    if (mission.getFieldTeam().getTeamMembers().contains(this) && mission.getState() == MissionState.IN_PROGRESS) {
                        return mission;
                    }
                }
            }
        }
        return null;
    }

}

package mas.apazniak.mas_final_s22326.model.DTO;


import lombok.*;
import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.model.Employment;
import mas.apazniak.mas_final_s22326.model.FieldTeam;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class FieldTeamDTO {

    private Long id;
    private Set<String> responsibilities;
    private Set<ArchaeologicalEmployeeRole> fieldTeamRole;
    private String firstName;
    private String lastName;
    private String nationality;
    private Set<Employment> employments;
    private FieldTeam fieldTeam;
}
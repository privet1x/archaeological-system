package mas.apazniak.mas_final_s22326.model.interfaces;

import mas.apazniak.mas_final_s22326.model.ArchaeologicalEmployee;
import mas.apazniak.mas_final_s22326.model.FieldTeam;
import mas.apazniak.mas_final_s22326.model.Mission;

import java.util.List;

public interface IAdministrator {
    void createFieldTeam();
    void updateFieldTeam(ArchaeologicalEmployee archaeologicalEmployee);
    List<FieldTeam> getFieldTeam();

    void createMission(Mission mission);
    void cancelMission(Long missionId);
    void trackMission(Long missionId);
}
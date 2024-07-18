package mas.apazniak.mas_final_s22326.model.interfaces;

import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.model.ArchaeologicalEmployee;

import java.util.List;
import java.util.Set;

public interface IArchaeologist {
    void changeArchaeologicalEmployeeRole(ArchaeologicalEmployee emp, ArchaeologicalEmployeeRole role);
    void finishCurrentMission();
    void analyzeFindings();
    Set<String> getExecutiveOperations();
    void setExecutiveOperations(List<String> executiveNotes);
}

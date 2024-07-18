package mas.apazniak.mas_final_s22326.model.Service;

import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.model.ArchaeologicalEmployee;
import mas.apazniak.mas_final_s22326.repository.ArchaeologicalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchaeologicalEmployeeService {

    @Autowired
    private ArchaeologicalEmployeeRepository archaeologicalEmployeeRepository;

    public List<ArchaeologicalEmployee> findByRole(ArchaeologicalEmployeeRole role) {
        return archaeologicalEmployeeRepository.findByFieldTeamRole(role);
    }

    public List<ArchaeologicalEmployee> findAll() {
        return (List<ArchaeologicalEmployee>) archaeologicalEmployeeRepository.findAll();
    }
}
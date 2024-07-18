package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.model.ArchaeologicalEmployee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArchaeologicalEmployeeRepository extends CrudRepository<ArchaeologicalEmployee, Long> {
    Iterable<ArchaeologicalEmployee> findAll();
    List<ArchaeologicalEmployee> findByFieldTeamRole(ArchaeologicalEmployeeRole role);

}

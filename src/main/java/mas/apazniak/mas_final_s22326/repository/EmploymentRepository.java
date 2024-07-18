package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.Employment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmploymentRepository extends CrudRepository<Employment, Long> {

    List<Employment> findAll();
}
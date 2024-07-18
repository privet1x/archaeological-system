package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
    List<Employee> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}

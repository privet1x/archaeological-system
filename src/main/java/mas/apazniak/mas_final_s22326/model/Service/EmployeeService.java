package mas.apazniak.mas_final_s22326.model.Service;

import mas.apazniak.mas_final_s22326.model.Employee;
import mas.apazniak.mas_final_s22326.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findByName(String name) {
        return employeeRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }
}

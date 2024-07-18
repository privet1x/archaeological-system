package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.model.Employee;
import mas.apazniak.mas_final_s22326.model.Service.EmployeeService;
import mas.apazniak.mas_final_s22326.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> list() {
        List<Employee> employees= (List<Employee>) employeeRepository.findAll();

        return employees;
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam String name) {
        return employeeService.findByName(name);
    }
}


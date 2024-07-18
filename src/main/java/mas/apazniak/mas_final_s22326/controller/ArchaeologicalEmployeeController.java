package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.model.ArchaeologicalEmployee;
import mas.apazniak.mas_final_s22326.model.Employee;
import mas.apazniak.mas_final_s22326.model.Service.ArchaeologicalEmployeeService;
import mas.apazniak.mas_final_s22326.repository.ArchaeologicalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archaeologicalEmployees")
public class ArchaeologicalEmployeeController {

    @Autowired
    private ArchaeologicalEmployeeService archaeologicalEmployeeService;

    @GetMapping
    public List<ArchaeologicalEmployee> list() {
        List<ArchaeologicalEmployee> archemps = (List<ArchaeologicalEmployee>) archaeologicalEmployeeService.findAll();

        return archemps;
    }

    @GetMapping("/searchByRole")
    public List<ArchaeologicalEmployee> searchByRole(@RequestParam ArchaeologicalEmployeeRole role) {
        return archaeologicalEmployeeService.findByRole(role);
    }
}


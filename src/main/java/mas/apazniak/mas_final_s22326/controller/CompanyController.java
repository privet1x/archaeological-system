package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.model.ResearchCompany;
import mas.apazniak.mas_final_s22326.repository.ResearchCompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final ResearchCompanyRepository companyRepository;

    public CompanyController(ResearchCompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<ResearchCompany> list() {
        List<ResearchCompany> companies = (List<ResearchCompany>) companyRepository.findAll();

        return companies;
    }

    @GetMapping("byId/{companyId}")
    public ResponseEntity<ResearchCompany> getCompanyById(@PathVariable Long companyId) {
        Optional<ResearchCompany> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            return new ResponseEntity<>(companyOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


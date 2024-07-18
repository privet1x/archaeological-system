package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.ResearchCompany;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResearchCompanyRepository extends CrudRepository<ResearchCompany, Long> {
    List<ResearchCompany> findAll();
}

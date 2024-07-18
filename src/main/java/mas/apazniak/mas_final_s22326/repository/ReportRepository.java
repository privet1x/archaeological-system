package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findAll();
}
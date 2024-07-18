package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.ExplorationVehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExplorationVehicleRepository  extends CrudRepository<ExplorationVehicle, Long> {
    List<ExplorationVehicle> findAll();
    Optional<ExplorationVehicle> findById(Long id);

    List<ExplorationVehicle> findByResearchCompany_Id(Long companyId);
}

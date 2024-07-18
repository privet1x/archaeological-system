package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.IntermediateLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IntermediateLocationRepository extends CrudRepository<IntermediateLocation, Long> {
    List<IntermediateLocation> findAll();

    Optional<IntermediateLocation> findById(Long id);
}

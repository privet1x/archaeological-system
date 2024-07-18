package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();

    Optional<Location> findById(Long id);
}

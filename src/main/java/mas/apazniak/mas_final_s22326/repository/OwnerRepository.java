package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAll();
}
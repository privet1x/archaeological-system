package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.Compartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompartmentRepository extends CrudRepository<Compartment, Long> {

    List<Compartment> findAll();
}


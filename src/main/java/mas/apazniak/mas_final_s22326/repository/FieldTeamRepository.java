package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.model.FieldTeam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FieldTeamRepository extends CrudRepository<FieldTeam, Long> {
    List<FieldTeam> findAll();
    Optional<FieldTeam> findById(Long id);
}


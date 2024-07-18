package mas.apazniak.mas_final_s22326.repository;

import mas.apazniak.mas_final_s22326.enumm.MissionState;
import mas.apazniak.mas_final_s22326.model.Mission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends CrudRepository<Mission, Long> {
    List<Mission> findAll();
    Optional<Mission> findById(Long id);

    List<Mission> findByStateNotIn(MissionState... states);
    List<Mission> findByStateIn(MissionState... states);
}

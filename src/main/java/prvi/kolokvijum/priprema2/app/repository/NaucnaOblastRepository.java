package prvi.kolokvijum.priprema2.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import prvi.kolokvijum.priprema2.app.model.NaucnaOblast;

@Repository
public interface NaucnaOblastRepository extends CrudRepository<NaucnaOblast, Long> {
}

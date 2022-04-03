package prvi.kolokvijum.priprema2.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import prvi.kolokvijum.priprema2.app.model.Nastavnik;
import prvi.kolokvijum.priprema2.app.model.Zvanje;

@Repository
public interface NastavnikRepository extends CrudRepository<Nastavnik, Long> {
    @Query("SELECT n FROM Nastavnik n WHERE n.zvanje = :zvanje")
    public Iterable<Nastavnik> findByZvanje(Zvanje zvanje);
}

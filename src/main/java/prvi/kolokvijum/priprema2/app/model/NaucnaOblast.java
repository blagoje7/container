package prvi.kolokvijum.priprema2.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class NaucnaOblast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "tipZvanja")
    private Set<Zvanje> zvanja;

    public NaucnaOblast() {
        super();
    }

    public NaucnaOblast(Long id, String naziv, Set<Zvanje> zvanja) {
        this.id = id;
        this.naziv = naziv;
        this.zvanja = zvanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Zvanje> getZvanja() {
        return zvanja;
    }

    public void setZvanja(Set<Zvanje> zvanja) {
        this.zvanja = zvanja;
    }
}

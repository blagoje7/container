package prvi.kolokvijum.priprema2.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Nastavnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = true)
    private String biografija;

    @Column(nullable = false)
    private String JMBG;

    @ManyToOne(optional = false)
    private Zvanje zvanje;

    public Nastavnik() {
        super();
    }

    public Nastavnik(Long id, String ime, String biografija, String JMBG, Zvanje zvanje) {
        this.id = id;
        this.ime = ime;
        this.biografija = biografija;
        this.JMBG = JMBG;
        this.zvanje = zvanje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public Zvanje getZvanje() {
        return zvanje;
    }

    public void setZvanje(Zvanje zvanje) {
        this.zvanje = zvanje;
    }
}

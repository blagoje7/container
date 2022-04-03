package prvi.kolokvijum.priprema2.app.dto;

import prvi.kolokvijum.priprema2.app.model.Zvanje;

import java.util.Set;

public class NaucnaOblastDTO {
    private Long id;
    private String naziv;
    private Set<Zvanje> zvanja;

    public NaucnaOblastDTO() {
        super();
    }

    public NaucnaOblastDTO(Long id, String naziv, Set<Zvanje> zvanja) {
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

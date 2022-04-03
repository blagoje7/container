package prvi.kolokvijum.priprema2.app.dto;

import prvi.kolokvijum.priprema2.app.model.Nastavnik;
import prvi.kolokvijum.priprema2.app.model.NaucnaOblast;
import prvi.kolokvijum.priprema2.app.model.TipZvanja;
import java.util.Date;
import java.util.Set;

public class ZvanjeDTO {
    private Long id;
    private Date datumIzbora;
    private Date datumPrestanka;
    private TipZvanja tipZvanja;
    private NaucnaOblast naucnaOblast;
    private Set<Nastavnik> nastavnici;

    public ZvanjeDTO() {
        super();
    }

    public ZvanjeDTO(Long id, Date datumIzbora, Date datumPrestanka, TipZvanja tipZvanja, NaucnaOblast naucnaOblast, Set<Nastavnik> nastavnici) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.tipZvanja = tipZvanja;
        this.naucnaOblast = naucnaOblast;
        this.nastavnici = nastavnici;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public TipZvanja getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanja tipZvanja) {
        this.tipZvanja = tipZvanja;
    }

    public NaucnaOblast getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public Set<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(Set<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }
}

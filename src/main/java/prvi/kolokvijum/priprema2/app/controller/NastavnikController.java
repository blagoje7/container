package prvi.kolokvijum.priprema2.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prvi.kolokvijum.priprema2.app.dto.NastavnikDTO;
import prvi.kolokvijum.priprema2.app.model.Nastavnik;
import prvi.kolokvijum.priprema2.app.model.Zvanje;
import prvi.kolokvijum.priprema2.app.service.NastavnikService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/nastavnici")
public class NastavnikController {
    @Autowired
    private NastavnikService nastavnikService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<NastavnikDTO>> getAllNastavnk() {
        ArrayList<NastavnikDTO> nastavniciDTO = new ArrayList<NastavnikDTO>();

        for (Nastavnik nastavnik : nastavnikService.findAll()) {
            nastavniciDTO.add(new NastavnikDTO(nastavnik.getId(), nastavnik.getIme(), nastavnik.getBiografija(), nastavnik.getJMBG(),
                    new Zvanje(nastavnik.getZvanje().getId(), nastavnik.getZvanje().getDatumIzbora(), nastavnik.getZvanje().getDatumPrestanka(), null, null, null)));
        }
        return new ResponseEntity<Iterable<NastavnikDTO>>(nastavniciDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/{nastavnikId}",method = RequestMethod.GET)
    public ResponseEntity<NastavnikDTO> getOneNastavnik(@PathVariable("nastavnikId") Long nastavnikId){
        Optional<Nastavnik> nastavnik = Optional.ofNullable(nastavnikService.findOne(nastavnikId).orElse(null));

        if(nastavnik.isPresent()){
            NastavnikDTO nastavnikDTO = new NastavnikDTO(nastavnik.get().getId(), nastavnik.get().getIme(),nastavnik.get().getBiografija(),nastavnik.get().getJMBG(),
                                        new Zvanje(nastavnik.get().getZvanje().getId(), nastavnik.get().getZvanje().getDatumIzbora(),nastavnik.get().getZvanje().getDatumPrestanka(), null,null,null));
            return new ResponseEntity<NastavnikDTO>(nastavnikDTO,HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.POST)
    public ResponseEntity<Nastavnik> createNastavnik(@RequestBody Nastavnik nastavnik){
        try{
            nastavnikService.save(nastavnik);
            return new ResponseEntity<Nastavnik>(nastavnik,HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Nastavnik>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{nastavnikId}",method = RequestMethod.PUT)
    public ResponseEntity<Nastavnik> updateNastavnik(@PathVariable("nastavnikId") Long nastavnikId,
                                            @RequestBody Nastavnik izmenjenNastavnik){
        Nastavnik nastavnik = nastavnikService.findOne(nastavnikId).orElse(null);
        if(nastavnik != null){
            izmenjenNastavnik.setId(nastavnikId);
            nastavnikService.save(izmenjenNastavnik);
            return new ResponseEntity<Nastavnik>(izmenjenNastavnik,HttpStatus.OK);
        }
        return new ResponseEntity<Nastavnik>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.DELETE)
    public ResponseEntity<Nastavnik> deleteNastavnik(@PathVariable("nastavnikId") Long nastavnikId){
        if(nastavnikService.findOne(nastavnikId).isPresent()){
            nastavnikService.delete(nastavnikId);
            return new ResponseEntity<Nastavnik>(HttpStatus.OK);
        }
        return new ResponseEntity<Nastavnik>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{zvanje}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<NastavnikDTO>> getNastavnikByZnanje(@PathVariable("zvanje") @RequestBody Zvanje zvanje){
        Iterable<Nastavnik> nastavnici = nastavnikService.findByZvanje(zvanje);
        ArrayList<NastavnikDTO> nastavniciDTO =  new ArrayList<NastavnikDTO>();
        for(Nastavnik nastavnik : nastavnici){
            nastavniciDTO.add(new NastavnikDTO(nastavnik.getId(), nastavnik.getIme(), nastavnik.getBiografija(), nastavnik.getJMBG(),
                    new Zvanje(nastavnik.getZvanje().getId(), nastavnik.getZvanje().getDatumIzbora(), nastavnik.getZvanje().getDatumPrestanka(), null, null, null)));
        }
        return new ResponseEntity<Iterable<NastavnikDTO>>(nastavniciDTO,HttpStatus.OK);
    }

}

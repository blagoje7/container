package prvi.kolokvijum.priprema2.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prvi.kolokvijum.priprema2.app.dto.ZvanjeDTO;
import prvi.kolokvijum.priprema2.app.model.NaucnaOblast;
import prvi.kolokvijum.priprema2.app.model.TipZvanja;
import prvi.kolokvijum.priprema2.app.model.Zvanje;
import prvi.kolokvijum.priprema2.app.service.ZvanjeService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/zvanja")
public class ZvanjeController {
    @Autowired
    private ZvanjeService zvanjeService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ZvanjeDTO>> getAllZvanje(){
        ArrayList<ZvanjeDTO> zvanjeDTOS = new ArrayList<ZvanjeDTO>();
        for(Zvanje zvanje : zvanjeService.findAll()){
            zvanjeDTOS.add(new ZvanjeDTO(zvanje.getId(),zvanje.getDatumIzbora(),zvanje.getDatumPrestanka(),zvanje.getTipZvanja(),zvanje.getNaucnaOblast(),null));
        }
        return new ResponseEntity<Iterable<ZvanjeDTO>>(zvanjeDTOS,HttpStatus.OK);
    }

    @RequestMapping(path = "/{zvanjeId}",method = RequestMethod.GET)
    public ResponseEntity<ZvanjeDTO> getOneZvanje(@PathVariable("zvanjeId") Long zvanjeId){
        Optional<Zvanje> zvanje = Optional.ofNullable(zvanjeService.findOne(zvanjeId).orElse(null));

        if(zvanje.isPresent()){
            ZvanjeDTO zvanjeDTO =new ZvanjeDTO(zvanje.get().getId() ,zvanje.get().getDatumIzbora(),zvanje.get().getDatumPrestanka(),
                    new TipZvanja(zvanje.get().getTipZvanja().getId(), zvanje.get().getTipZvanja().getNaziv(),null),
                    new NaucnaOblast(zvanje.get().getNaucnaOblast().getId(),zvanje.get().getNaucnaOblast().getNaziv(),null), null);
            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO,HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Zvanje> createZvanje(@RequestBody Zvanje zvanje)  {
        try {
            zvanjeService.save(zvanje);
            return new ResponseEntity<Zvanje>(zvanje,HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Zvanje>(zvanje,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.PUT)
    public ResponseEntity<Zvanje> updateZvanje(@PathVariable("zvanjeId") Long zvanjeId,
                                               @RequestBody Zvanje izmenjenoZvanje){
        Zvanje zvanje = zvanjeService.findOne(zvanjeId).orElse(null);
        if(zvanje != null){
            izmenjenoZvanje.setId(zvanjeId);
            zvanjeService.save(izmenjenoZvanje);
            return new ResponseEntity<Zvanje>(izmenjenoZvanje,HttpStatus.OK);
        }
        return new ResponseEntity<Zvanje>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{zvanjeId}",method = RequestMethod.DELETE)
    public ResponseEntity<Zvanje> deleteZvanje(@PathVariable("zvanjeId") Long zvanjeId){
        if(zvanjeService.findOne(zvanjeId).isPresent()){
            zvanjeService.delete(zvanjeId);
            return new ResponseEntity<Zvanje>(HttpStatus.OK);
        }
        return new ResponseEntity<Zvanje>(HttpStatus.NOT_FOUND);
    }
}

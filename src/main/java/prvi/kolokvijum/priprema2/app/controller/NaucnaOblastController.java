package prvi.kolokvijum.priprema2.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prvi.kolokvijum.priprema2.app.dto.NaucnaOblastDTO;
import prvi.kolokvijum.priprema2.app.dto.ZvanjeDTO;
import prvi.kolokvijum.priprema2.app.model.NaucnaOblast;
import prvi.kolokvijum.priprema2.app.model.TipZvanja;
import prvi.kolokvijum.priprema2.app.model.Zvanje;
import prvi.kolokvijum.priprema2.app.service.NaucnaOblastService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/naucneoblasti")
public class NaucnaOblastController {
    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @RequestMapping(path = "",method = RequestMethod.GET)
    public ResponseEntity<Iterable<NaucnaOblastDTO>> getAllNaucnaOblast(){
        ArrayList<NaucnaOblastDTO> naucnaOblastDTOS = new ArrayList<NaucnaOblastDTO>();

        for(NaucnaOblast naucnaOblast : naucnaOblastService.findAll()){
            naucnaOblastDTOS.add(new NaucnaOblastDTO(naucnaOblast.getId(),naucnaOblast.getNaziv(),null));
        }
        return new ResponseEntity<Iterable<NaucnaOblastDTO>>(naucnaOblastDTOS,HttpStatus.OK);
    }

    @RequestMapping(path = "/{naucnaOblastId}",method = RequestMethod.GET)
    public ResponseEntity<NaucnaOblastDTO> getOneNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId){
        Optional<NaucnaOblast> naucnaOblast = Optional.ofNullable(naucnaOblastService.findOne(naucnaOblastId).orElse(null));

        if(naucnaOblast.isPresent()){
            NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(naucnaOblast.get().getId(),naucnaOblast.get().getNaziv(),null);
            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO,HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<NaucnaOblast> createZvanje(@RequestBody NaucnaOblast naucnaOblast)  {
        try {
            naucnaOblastService.save(naucnaOblast);
            return new ResponseEntity<NaucnaOblast>(naucnaOblast,HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<NaucnaOblast>(naucnaOblast,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{naucnaOblast}", method = RequestMethod.PUT)
    public ResponseEntity<NaucnaOblast> updateNaucnaOblast(@PathVariable("naucnaOblast") Long naucnaOblastId,
                                               @RequestBody NaucnaOblast izmenjenaNaucnaOblast){
        NaucnaOblast naucnaOblast = naucnaOblastService.findOne(naucnaOblastId).orElse(null);
        if(naucnaOblast != null){
            izmenjenaNaucnaOblast.setId(naucnaOblastId);
            naucnaOblastService.save(izmenjenaNaucnaOblast);
            return new ResponseEntity<NaucnaOblast>(izmenjenaNaucnaOblast,HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblast>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{naucnaOblast}",method = RequestMethod.DELETE)
    public ResponseEntity<NaucnaOblast> deleteNaucnaOblast(@PathVariable("naucnaOblast") Long naucnaOblastId) {
        if (naucnaOblastService.findOne(naucnaOblastId).isPresent()) {
            naucnaOblastService.delete(naucnaOblastId);
            return new ResponseEntity<NaucnaOblast>(HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblast>(HttpStatus.NOT_FOUND);
    }
}

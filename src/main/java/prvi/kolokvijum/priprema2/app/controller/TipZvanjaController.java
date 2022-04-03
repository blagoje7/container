package prvi.kolokvijum.priprema2.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prvi.kolokvijum.priprema2.app.dto.TipZvanjaDTO;
import prvi.kolokvijum.priprema2.app.model.TipZvanja;
import prvi.kolokvijum.priprema2.app.service.TipZvanjaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/tipovizvanja")
public class TipZvanjaController {
    @Autowired
    private TipZvanjaService tipZvanjaService;

    @RequestMapping(path = "",method = RequestMethod.GET)
    public ResponseEntity<Iterable<TipZvanjaDTO>> getAllTipZvanja(){
        ArrayList<TipZvanjaDTO> tipZvanjaDTOS = new ArrayList<TipZvanjaDTO>();
        for(TipZvanja tipZvanja : tipZvanjaService.findAll()){
            tipZvanjaDTOS.add(new TipZvanjaDTO(tipZvanja.getId(),tipZvanja.getNaziv(),null));
        }
        return new ResponseEntity<Iterable<TipZvanjaDTO>>(tipZvanjaDTOS, HttpStatus.OK);
    }

    @RequestMapping(path = "/{tipzvanjaid}", method = RequestMethod.GET)
    public ResponseEntity<TipZvanjaDTO> getOneTipZvanja(@PathVariable("tipzvanjaid")Long tipzvanjaid){
        Optional<TipZvanja> tipZvanja = tipZvanjaService.findOne(tipzvanjaid);
        if(tipZvanja.isPresent()){
            TipZvanjaDTO tipZvanjaDTO = new TipZvanjaDTO(tipZvanja.get().getId(), tipZvanja.get().getNaziv(),null);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO,HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<TipZvanja> createTipZvanja(@RequestBody TipZvanja tipZvanja){
        try{
            tipZvanjaService.save(tipZvanja);
            return new ResponseEntity<TipZvanja>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<TipZvanja>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{tipzvanjaid}", method = RequestMethod.PUT)
    public ResponseEntity<TipZvanja> updateTipZvanja(@PathVariable("tipzvanjaid")Long tipzvanjaId,
                                                     @RequestBody TipZvanja izmenjenTipZvanja){
        TipZvanja tipZvanja = tipZvanjaService.findOne(tipzvanjaId).orElse(null);
        if(tipZvanja != null){
            izmenjenTipZvanja.setId(tipzvanjaId);
            tipZvanjaService.save(izmenjenTipZvanja);
            return new ResponseEntity<TipZvanja>(izmenjenTipZvanja,HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanja>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{tipzvanjaid}", method = RequestMethod.DELETE)
    public ResponseEntity<TipZvanja> deleteTipZvanja(@PathVariable("tipzvanjaid")Long tipzvanjaid){
        if(tipZvanjaService.findOne(tipzvanjaid).isPresent()){
            tipZvanjaService.delete(tipzvanjaid);
            return new ResponseEntity<TipZvanja>(HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanja>(HttpStatus.NOT_FOUND);
    }
}

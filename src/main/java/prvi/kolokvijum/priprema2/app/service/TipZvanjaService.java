package prvi.kolokvijum.priprema2.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prvi.kolokvijum.priprema2.app.model.TipZvanja;
import prvi.kolokvijum.priprema2.app.repository.TipZvanjaRepository;

import java.util.Optional;

@Service
public class TipZvanjaService {
    @Autowired
    private TipZvanjaRepository tipZvanjaRepository;

    public TipZvanjaService() {
        super();
    }

    public TipZvanjaRepository getTipZvanjaRepository() {
        return tipZvanjaRepository;
    }

    public void setTipZvanjaRepository(TipZvanjaRepository tipZvanjaRepository) {
        this.tipZvanjaRepository = tipZvanjaRepository;
    }

    public Iterable<TipZvanja> findAll(){
        return this.tipZvanjaRepository.findAll();
    }

    public Optional<TipZvanja> findOne(Long id){
        return this.tipZvanjaRepository.findById(id);
    }

    public TipZvanja save(TipZvanja tipZvanja){
        return this.tipZvanjaRepository.save(tipZvanja);
    }

    public void delete(TipZvanja tipZvanja){
        this.tipZvanjaRepository.delete(tipZvanja);
    }

    public void delete(Long id){
        this.tipZvanjaRepository.deleteById(id);
    }
}

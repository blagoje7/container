package prvi.kolokvijum.priprema2.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prvi.kolokvijum.priprema2.app.model.NaucnaOblast;
import prvi.kolokvijum.priprema2.app.repository.NaucnaOblastRepository;

import java.util.Optional;

@Service
public class NaucnaOblastService {
    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;

    public NaucnaOblastService() {
        super();
    }

    public NaucnaOblastRepository getNaucnaOblastRepository() {
        return naucnaOblastRepository;
    }

    public void setNaucnaOblastRepository(NaucnaOblastRepository naucnaOblastRepository) {
        this.naucnaOblastRepository = naucnaOblastRepository;
    }

    public Iterable<NaucnaOblast> findAll(){
        return this.naucnaOblastRepository.findAll();
    }

    public Optional<NaucnaOblast> findOne(Long id){
        return this.naucnaOblastRepository.findById(id);
    }

    public NaucnaOblast save(NaucnaOblast naucnaOblast){
        return this.naucnaOblastRepository.save(naucnaOblast);
    }

    public void delete(NaucnaOblast naucnaOblast){
        this.naucnaOblastRepository.delete(naucnaOblast);
    }

    public void delete(Long id){
        this.naucnaOblastRepository.deleteById(id);
    }
}

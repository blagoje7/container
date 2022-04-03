package prvi.kolokvijum.priprema2.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prvi.kolokvijum.priprema2.app.model.Zvanje;
import prvi.kolokvijum.priprema2.app.repository.ZvanjeRepository;

import java.util.Optional;

@Service
public class ZvanjeService {
    @Autowired
    private ZvanjeRepository zvanjeRepository;

    public ZvanjeService() {
        super();
    }

    public ZvanjeRepository getZvanjeRepository() {
        return zvanjeRepository;
    }

    public void setZvanjeRepository(ZvanjeRepository zvanjeRepository) {
        this.zvanjeRepository = zvanjeRepository;
    }

    public Iterable<Zvanje> findAll(){
        return this.zvanjeRepository.findAll();
    }

    public Optional<Zvanje> findOne(Long id){
        return this.zvanjeRepository.findById(id);
    }

    public Zvanje save(Zvanje zvanje){
        return this.zvanjeRepository.save(zvanje);
    }

    public void delete(Zvanje zvanje){
        this.zvanjeRepository.delete(zvanje);
    }

    public void delete(Long id){
        this.zvanjeRepository.deleteById(id);
    }

}

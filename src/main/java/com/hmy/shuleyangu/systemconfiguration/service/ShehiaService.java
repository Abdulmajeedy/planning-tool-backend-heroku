package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ShehiaRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShehiaService {
    private final ShehiaRepository shehiaRepository;

    @Autowired
    public ShehiaService(ShehiaRepository shehiaRepository){
        this.shehiaRepository = shehiaRepository;
    }

    public List<Shehia> getShehia() {
        return (List<Shehia>) shehiaRepository.findAll();
    }

    public void addNewShehia(Shehia shehia) {
        shehiaRepository.save(shehia);

    }
    public Optional<Shehia> getShehiaById(UUID shehiaId){

        return shehiaRepository.findById(shehiaId);
    }

    public void deleteShehia(UUID shehiaId){
        shehiaRepository.deleteById(shehiaId);
    }

    public void updateShehia(UUID shehiaId, Shehia shehia) {
        shehiaRepository.findById(shehiaId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Shehia with Id "+ shehiaId + " does not exist"
                ));


        shehia.setShehiaId(shehiaId);
        shehiaRepository.save(shehia);

    }


}


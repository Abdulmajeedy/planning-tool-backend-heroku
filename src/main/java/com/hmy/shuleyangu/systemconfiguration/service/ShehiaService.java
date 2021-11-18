package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ShehiaRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

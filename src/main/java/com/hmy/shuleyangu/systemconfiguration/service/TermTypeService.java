package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.TermTypeRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermTypeService {
    private final TermTypeRepository termTypeRepository;

    @Autowired
    public TermTypeService(TermTypeRepository termTypeRepository){
        this.termTypeRepository = termTypeRepository;
    }

    public List<TermTypes> getTermTypes() {
        return (List<TermTypes>) termTypeRepository.findAll();
    }

    public void addNewTermType(TermTypes termTypes) {
        termTypeRepository.save(termTypes);

    }


}

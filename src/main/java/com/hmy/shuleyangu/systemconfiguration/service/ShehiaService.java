package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.repository.ShehiaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShehiaService {
    private final ShehiaRepository shehiaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShehiaService(ShehiaRepository shehiaRepository, ModelMapper modelMapper){
        this.shehiaRepository = shehiaRepository;
        this.modelMapper = modelMapper;
    }

    public List<Shehia> getShehia(PageRequest pageRequest) {
        return shehiaRepository.findAll(pageRequest).getContent();
    }
    public Shehia addNewShehia(Shehia shehia) {
        shehiaRepository.save(shehia);
        return shehia;
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


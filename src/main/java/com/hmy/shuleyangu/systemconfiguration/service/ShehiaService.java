package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.repository.ShehiaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void addNewShehia(ShehiaRequestDto sh) {
        Shehia s = new Shehia();
        s.setShehiaCode(sh.getShehiaCode());
        s.setShehiaName(sh.getShehiaName());
        shehiaRepository.save(s);
    }

    public Optional<Shehia> getShehiaById(String shehiaId){
        return shehiaRepository.findById(shehiaId);
    }


    public void deleteShehia(String shehiaId){
        shehiaRepository.deleteById(shehiaId);
    }

    public void updateShehia(String shehiaId, Shehia shehia) {
        shehiaRepository.findById(shehiaId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Shehia with Id "+ shehiaId + " does not exist"
                ));
        shehia.setShehiaId(shehiaId);
        shehiaRepository.save(shehia);

    }


}


package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.Extracariculum;
import com.hmy.shuleyangu.systemconfiguration.repository.ExtracariculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtracariculumService {
    @Autowired
    private ExtracariculumRepository extracariculumRepository;
    @Autowired
    public ExtracariculumService(ExtracariculumRepository extracariculumRepository) {
        this.extracariculumRepository = extracariculumRepository;
    }

    public List<Extracariculum> findAllExtracariculum(PageRequest pageRequest) {
        return extracariculumRepository.findAll(pageRequest).getContent();
    }
    public Optional<Extracariculum> getExtracariculumById(String cariculumId){
        return extracariculumRepository.findById(cariculumId);
    }

    public ResponseEntity<ExtracariculumResponseDto> addNewExtracaricular(ExtracariculumRequestDto erd) {
        Extracariculum e = new Extracariculum();
        e.setRemark(erd.getRemark());
        e.setRate(erd.getRate());
        extracariculumRepository.save(e);

        ExtracariculumResponseDto responseDto = new ExtracariculumResponseDto();

        responseDto.setRemark(e.getRemark());
        responseDto.setRate(e.getRate());
        responseDto.setCreatedDate(e.getCreatedDate());
        responseDto.setCreatedBy(e.getCreatedBy());
        responseDto.setModifiedDate(e.getModifiedDate());
        responseDto.setModifiedBy(e.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }



}

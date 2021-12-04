package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ShiftRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShiftResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SpecializationRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SpecializationResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Shift;
import com.hmy.shuleyangu.systemconfiguration.models.Specialization;
import com.hmy.shuleyangu.systemconfiguration.repository.ShiftRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.SpecializationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {
    private ModelMapper modelMapper;
    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    public SpecializationService(ModelMapper modelMapper, SpecializationRepository specializationRepository){
        this.modelMapper = modelMapper;
        this.specializationRepository = specializationRepository;
    }

    public List<Specialization> findSpecialization(PageRequest pageRequest) {
        return specializationRepository.findAll(pageRequest).getContent();
    }
    public ResponseEntity<SpecializationResponseDto> addNewSpecialization(SpecializationRequestDto specializationRequestDto) {
        Specialization specialization = new Specialization();
        specialization.setSpecializationName(specializationRequestDto.getSpecializationName());
        specialization.setSpecializationDescription(specialization.getSpecializationDescription());
        specialization.setStatus(specializationRequestDto.getStatus());
        specializationRepository.save(specialization);

        SpecializationResponseDto responseDto = new SpecializationResponseDto();
        responseDto.setSpecializationId(specialization.getSpecializationId());
        responseDto.setSpecializationName(specialization.getSpecializationName());
        responseDto.setStatus(specialization.getStatus());
        responseDto.setCreatedDate(specialization.getCreatedDate());
        responseDto.setCreatedBy(specialization.getCreatedBy());
        responseDto.setModifiedDate(specialization.getModifiedDate());
        responseDto.setModifiedBy(specialization.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    public Optional<Specialization> getSpecializationById(String specializationId){
        return specializationRepository.findById(specializationId);
    }

    public void deleteSpecialization(String specializationId){
        specializationRepository.deleteById(specializationId);
    }

}


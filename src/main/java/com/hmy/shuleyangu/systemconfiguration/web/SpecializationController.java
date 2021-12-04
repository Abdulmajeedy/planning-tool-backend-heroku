package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.SpecializationRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SpecializationResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Specialization;
import com.hmy.shuleyangu.systemconfiguration.repository.SpecializationRepository;
import com.hmy.shuleyangu.systemconfiguration.service.SpecializationService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.SpecializationApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SpecializationController implements SpecializationApi {

    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private SpecializationRepository specializationRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SpecializationController(SpecializationService specializationService, ModelMapper modelMapper){
        this.specializationService=specializationService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<SpecializationResponseDto>> getSpecialization(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Specialization> specializations = specializationService.findSpecialization(pageRequest);
        List<SpecializationResponseDto> specializationResponseDto = new ArrayList<>();
        for(Specialization sp:specializations)
        {
            SpecializationResponseDto responseDto = new SpecializationResponseDto();
            responseDto.setSpecializationId(sp.getSpecializationId());
            responseDto.setSpecializationName(sp.getSpecializationName());
            responseDto.setSpecializationDescription(sp.getSpecializationDescription());
            responseDto.setStatus(sp.getStatus());
            responseDto.setCreatedDate(sp.getCreatedDate());
            responseDto.setCreatedBy(sp.getCreatedBy());
            responseDto.setModifiedDate(sp.getModifiedDate());
            responseDto.setModifiedBy(sp.getModifiedBy());
            specializationResponseDto.add(responseDto);
        }
        return ResponseEntity.ok(specializationResponseDto);
    }

    public ResponseEntity<SpecializationResponseDto> registerNewSpecialization(SpecializationRequestDto specializationRequestDto){
        return specializationService.addNewSpecialization(specializationRequestDto);
    }

    public ResponseEntity<SpecializationResponseDto> getSpecializationById(String specializationId) {
        {
            Optional<Specialization> sp = specializationService.getSpecializationById(specializationId);
            if(!sp.isPresent())
            {
                return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
            }
            else {
                Specialization s = sp.get();
                SpecializationResponseDto responseDto = new SpecializationResponseDto();
                responseDto.setSpecializationId(s.getSpecializationId());
                responseDto.setSpecializationName(s.getSpecializationName());
                responseDto.setSpecializationDescription(s.getSpecializationDescription());
                responseDto.setStatus(s.getStatus());
                responseDto.setCreatedDate(s.getCreatedDate());
                responseDto.setCreatedBy(s.getCreatedBy());
                responseDto.setModifiedDate(s.getModifiedDate());
                responseDto.setModifiedBy(s.getModifiedBy());
                responseDto.setStatus(s.getStatus());
                return ResponseEntity.ok(responseDto);
            }}
    }
    public void deleteById(String specializationId){
        specializationService.deleteSpecialization(specializationId);

    }

}

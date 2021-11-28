package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessReqDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.ContAssessmentType;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ContAssessmentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContAssessmentTypeService {
    private final ContAssessmentTypeRepository contAssessTypeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContAssessmentTypeService(ContAssessmentTypeRepository contAssessmentTypeRepository,
                                     ModelMapper modelMapper){
        this.contAssessTypeRepository = contAssessmentTypeRepository;
        this.modelMapper = modelMapper;
    }

    public List<ContAssessmentType> findAllContAssessTypes(PageRequest pageRequest) {
        return contAssessTypeRepository.findAll(pageRequest).getContent();
    }

    public Optional<ContAssessmentType> getContAssesTypeById(String contAssessTypeId){
        return contAssessTypeRepository.findById(contAssessTypeId);
    }
    public void addNewContAssessType(ContAssessReqDto cad) {
        ContAssessmentType c = new ContAssessmentType();
        c.setContAssessmentTypeName(cad.getContAssessmentTypeName());
        c.setStatus(cad.getStatus());
        contAssessTypeRepository.save(c);
    }


    public Optional<ContAssessmentType> getContAssessTypeById(String contAssesTypeId){
        return contAssessTypeRepository.findById(contAssesTypeId);
    }

    public void deleteContAssesType(String contAssesTypeId){

        contAssessTypeRepository.deleteById(contAssesTypeId);
    }


}

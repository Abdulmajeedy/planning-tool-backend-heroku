package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessReqDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessRespDto;
import com.hmy.shuleyangu.systemconfiguration.models.ContAssessmentType;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ContAssessmentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContAssesTypeService {
    private ModelMapper modelMapper;
    @Autowired
    private ContAssessmentTypeRepository contAssessTypeRepository;

    @Autowired
    public ContAssesTypeService(ContAssessmentTypeRepository contAssessmentTypeRepository,
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

    public ContAssessRespDto addNewContAssessType(ContAssessReqDto cad) {
        ModelMapper modelMapper = new ModelMapper();
        ContAssessmentType c =  modelMapper.map(cad,ContAssessmentType.class);
        ContAssessmentType ca = contAssessTypeRepository.save(c);
        ContAssessRespDto respDto = modelMapper.map(ca,ContAssessRespDto.class);
        return respDto;
    }


    public Optional<ContAssessmentType> getContAssessTypeById(String contAssesTypeId){
        return contAssessTypeRepository.findById(contAssesTypeId);
    }

    public void deleteContAssesType(String contAssesTypeId){
        contAssessTypeRepository.deleteById(contAssesTypeId);
    }

    public void updateContAssType(String contAssesTypeId, ContAssessmentType cat) {
        contAssessTypeRepository.findById(contAssesTypeId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Continuous Assessment Task with Id "+ contAssesTypeId + " does not exist"
                ));
        cat.setContAssessmentTypeId(contAssesTypeId);
        contAssessTypeRepository.save(cat);

    }

}

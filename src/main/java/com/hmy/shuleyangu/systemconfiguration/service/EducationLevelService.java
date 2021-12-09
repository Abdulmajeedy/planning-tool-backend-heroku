package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.EducationLevelRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.EducationLevelResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import com.hmy.shuleyangu.systemconfiguration.repository.EducationLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationLevelService {
    @Autowired
    private EducationLevelRepository educationLevelRepository;
    @Autowired
    public EducationLevelService(EducationLevelRepository educationLevelRepository){
        this.educationLevelRepository = educationLevelRepository;
    }

    public Optional<EducationLevel> getEducationLevelById(String educationLevelId){
        return educationLevelRepository.findById(educationLevelId);
    }

    public List<EducationLevel> findEducationLevels(PageRequest pageRequest) {
        return educationLevelRepository.findAll(pageRequest).getContent();
    }

    public ResponseEntity<EducationLevelResponseDto> addNewEducationLevel(EducationLevelRequestDto elr) {
        EducationLevel e = new EducationLevel();
        e.setEducationLevelName(elr.getEducationLevelName());
        e.setDescription(elr.getDescription());
        e.setStatus(elr.getStatus());
        educationLevelRepository.save(e);

        EducationLevelResponseDto responseDto = new EducationLevelResponseDto();
        responseDto.setEducationLevelId(e.getEducationLevelId());
        responseDto.setEducationLevelName(e.getEducationLevelName());
        responseDto.setDescription(e.getDescription());
        responseDto.setStatus(e.getStatus());
        responseDto.setCreatedDate(e.getCreatedDate());
        responseDto.setCreatedBy(e.getCreatedBy());
        responseDto.setModifiedDate(e.getModifiedDate());
        responseDto.setModifiedBy(e.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
    public void updateEducationLevel(String educationLevelId, EducationLevel educationLevel) {
        educationLevelRepository.findById(educationLevelId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "District with Id " + educationLevelId + " does not exist"
                ));

        educationLevel.setEducationLevelId(educationLevelId);
        educationLevelRepository.save(educationLevel);
    }

}


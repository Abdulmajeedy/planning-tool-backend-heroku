package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SubjectRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SubjectResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Subject;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EducationLevelService educationLevelService;


    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAllSubjects(PageRequest pageRequest) {
        return subjectRepository.findAll(pageRequest).getContent();
    }
    public Optional<Subject> getSubjectById(String subjectId){

        return subjectRepository.findById(subjectId);
    }

    public ResponseEntity<SubjectResponseDto> addNewSubject(SubjectRequestDto subjectRequesDto) {
        Optional<EducationLevel> ed = educationLevelService.getEducationLevelById(subjectRequesDto.getEducationLevelId());
        if(!ed.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Subject s = new Subject();
        EducationLevel e = ed.get();

        s.setEducationLevel(e);
        s.setSubjectName(subjectRequesDto.getSubjectName());
        s.setEmisCode(subjectRequesDto.getEmisCode());
        s.setNectaCode(subjectRequesDto.getNectaCode());
        s.setShortCode(subjectRequesDto.getShortCode());
        s.setSubjectSchema(subjectRequesDto.getSubjectSchema());
        s.setStatus(subjectRequesDto.getStatus());
        subjectRepository.save(s);

        SubjectResponseDto responseDto = new SubjectResponseDto();
        responseDto.setSubjectId(s.getSubjectId());
        responseDto.setSubjectName(s.getSubjectName());
        responseDto.setNectaCode(s.getNectaCode());
        responseDto.setEmisCode(s.getEmisCode());
        responseDto.setSubjectSchema(s.getSubjectSchema());
        responseDto.setShortCode(s.getShortCode());
        responseDto.setSubjectRegYear(s.getSubjectRegYear());
        responseDto.setStatus(s.getStatus());
        responseDto.setEducationLevelId(e.getEducationLevelId());
        responseDto.setCreatedDate(e.getCreatedDate());
        responseDto.setCreatedBy(e.getCreatedBy());
        responseDto.setModifiedDate(e.getModifiedDate());
        responseDto.setModifiedBy(e.getModifiedBy());
        responseDto.setCreatedDate(e.getCreatedDate());
        responseDto.setCreatedBy(e.getCreatedBy());
        responseDto.setModifiedDate(e.getModifiedDate());
        responseDto.setModifiedBy(e.getModifiedBy());
        return  ResponseEntity.ok(responseDto);
    }

}

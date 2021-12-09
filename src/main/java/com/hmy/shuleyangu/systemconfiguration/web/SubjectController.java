package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SubjectRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SubjectResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Subject;
import com.hmy.shuleyangu.systemconfiguration.repository.SubjectRepository;
import com.hmy.shuleyangu.systemconfiguration.service.EducationLevelService;
import com.hmy.shuleyangu.systemconfiguration.service.SubjectService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.SubjectApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController implements SubjectApi {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EducationLevelService educationLevelService;

    public SubjectController(SubjectService subjectService,EducationLevelService educationLevelService){
        this.subjectService=subjectService;
        this.educationLevelService=educationLevelService;
    }

    @Override
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(int page, int size)
    {

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Subject> subjects = subjectService.findAllSubjects(pageRequest);

        List<SubjectResponseDto> subj = new ArrayList<>();
        for(Subject s:subjects)
        {
            SubjectResponseDto responseDto = new SubjectResponseDto();
            responseDto.setSubjectId(s.getSubjectId());
            responseDto.setNectaCode(s.getNectaCode());
            responseDto.setEmisCode(s.getEmisCode());
            responseDto.setShortCode(s.getShortCode());
            responseDto.setSubjectRegYear(s.getSubjectRegYear());
            responseDto.setSubjectName(s.getSubjectName());
            responseDto.setSubjectSchema(s.getSubjectSchema());
            responseDto.setShortCode(s.getShortCode());
            responseDto.setStatus(s.getStatus());
            responseDto.setEducationLevelId(s.getEducationLevel().getEducationLevelId());
            responseDto.setCreatedDate(s.getCreatedDate());
            responseDto.setCreatedBy(s.getCreatedBy());
            responseDto.setModifiedDate(s.getModifiedDate());
            responseDto.setModifiedBy(s.getModifiedBy());
            subj.add(responseDto);
        }
        return ResponseEntity.ok(subj);

    }

    @Override
    public ResponseEntity<SubjectResponseDto> registerNewSubject(SubjectRequestDto subjectRequestDto) {
        return subjectService.addNewSubject(subjectRequestDto);
    }

    @Override
    public ResponseEntity<SubjectResponseDto> getSubjectById(String subjectId) {
        Optional<Subject> subj = subjectService.getSubjectById(subjectId);
        if(!subj.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
        }
        else
        {
            Subject s = subj.get();
            SubjectResponseDto responseDto = new SubjectResponseDto();
            responseDto.setSubjectId(s.getSubjectId());
            responseDto.setSubjectName(s.getSubjectName());
            responseDto.setSubjectSchema(s.getSubjectSchema());
            responseDto.setShortCode(s.getShortCode());
            responseDto.setNectaCode(s.getNectaCode());
            responseDto.setEmisCode(s.getEmisCode());
            responseDto.setStatus(s.getStatus());
            responseDto.setCreatedDate(s.getCreatedDate());
            responseDto.setCreatedBy(s.getCreatedBy());
            responseDto.setModifiedDate(s.getModifiedDate());
            responseDto.setModifiedBy(s.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }
}

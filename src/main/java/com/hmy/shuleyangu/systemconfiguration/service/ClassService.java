package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ClassRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ClassResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Classes;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import com.hmy.shuleyangu.systemconfiguration.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private EducationLevelService educationLevelService;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Classes> findAllClasses(PageRequest pageRequest) {
        return classRepository.findAll(pageRequest).getContent();
    }

    public Optional<Classes> getClassById(String classId){

        return classRepository.findById(classId);
    }

    public ResponseEntity<ClassResponseDto> addNewClass(ClassRequestDto classRequestDto) {
        Optional<EducationLevel> el = educationLevelService.getEducationLevelById(classRequestDto.getEducationLevelId());
        if(!el.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Classes c = new Classes();
        EducationLevel e = el.get();

        c.setEducationLevel(e);
        c.setClassName(classRequestDto.getClassName());
        c.setAlternativeName(classRequestDto.getAlternativeName());
        c.setStatus(classRequestDto.getStatus());
        classRepository.save(c);

        ClassResponseDto responseDto = new ClassResponseDto();
        responseDto.setClassId(c.getClassId());
        responseDto.setClassName(c.getClassName());
        responseDto.setAlternativeName(c.getAlternativeName());
        responseDto.setStatus(c.getStatus());
        responseDto.setCreatedDate(c.getCreatedDate());
        responseDto.setCreatedBy(c.getCreatedBy());
        responseDto.setModifiedDate(c.getModifiedDate());
        responseDto.setModifiedBy(c.getModifiedBy());
        responseDto.setEducationLevelId(e.getEducationLevelId());
        return  ResponseEntity.ok(responseDto);
    }
}

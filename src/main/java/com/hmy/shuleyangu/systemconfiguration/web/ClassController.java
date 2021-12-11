package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.ClassRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ClassResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Classes;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.repository.ClassRepository;
import com.hmy.shuleyangu.systemconfiguration.service.ClassService;
import com.hmy.shuleyangu.systemconfiguration.service.EducationLevelService;
import com.hmy.shuleyangu.systemconfiguration.web.api.ClassApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClassController  implements ClassApi {
    @Autowired
    private ClassService classService;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private EducationLevelService educationLevelService;

    public ClassController(ClassService classService,EducationLevelService educationLevelService){
        this.classService=classService;
        this.educationLevelService=educationLevelService;
    }

    public ResponseEntity<List<ClassResponseDto>> getClasses(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Classes> classes = classService.findAllClasses(pageRequest);

        List<ClassResponseDto> cls = new ArrayList<>();
        for(Classes c:classes)
        {
            ClassResponseDto responseDto = new ClassResponseDto();
            responseDto.setClassId(c.getClassId());
            responseDto.setClassName(c.getClassName());
            responseDto.setAlternativeName(c.getAlternativeName());
            responseDto.setStatus(c.getStatus());
            responseDto.setCreatedDate(c.getCreatedDate());
            responseDto.setCreatedBy(c.getCreatedBy());
            responseDto.setModifiedDate(c.getModifiedDate());
            responseDto.setModifiedBy(c.getModifiedBy());
            responseDto.setEducationLevelId(c.getEducationLevel().getEducationLevelId());
            cls.add(responseDto);
        }
        return ResponseEntity.ok(cls);

    }

    public ResponseEntity<ClassResponseDto> registerClass(ClassRequestDto classRequestDto) {
        return classService.addNewClass(classRequestDto);
    }
}

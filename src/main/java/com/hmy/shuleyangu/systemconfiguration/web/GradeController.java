package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.GradeRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.GradeResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Grades;
import com.hmy.shuleyangu.systemconfiguration.repository.GradeRepository;
import com.hmy.shuleyangu.systemconfiguration.service.*;
import com.hmy.shuleyangu.systemconfiguration.web.api.GradeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GradeController implements GradeApi {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private RemarkService remarkService;
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private EducationLevelService educationLevelService;

    public GradeController(GradeService gradeService,RemarkService remarkService, AcademicYearService academicYearService,EducationLevelService educationLevelService){
        this.gradeService=gradeService;
        this.remarkService=remarkService;
        this.academicYearService = academicYearService;
    }
    public ResponseEntity<List<GradeResponseDto>> getGrades(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Grades> grades = gradeService.findAllGrades(pageRequest);

        List<GradeResponseDto> grd = new ArrayList<>();
        for(Grades g:grades)
        {
            GradeResponseDto responseDto = new GradeResponseDto();
            responseDto.setGradeId(g.getGradeId());
            responseDto.setGradeName(g.getGradeName());
            responseDto.setGradePoint(g.getGradePoint());
            responseDto.setGradeMaxMark(g.getGradeMaxMark());
            responseDto.setGradeMinMark(g.getGradeMinMark());
            responseDto.setStatus(g.getStatus());
            responseDto.setCreatedDate(g.getCreatedDate());
            responseDto.setCreatedBy(g.getCreatedBy());
            responseDto.setModifiedDate(g.getModifiedDate());
            responseDto.setModifiedBy(g.getModifiedBy());
            responseDto.setRemarkId(g.getRemarks().getRemarkId());
            responseDto.setAcademicYearId(g.getAcademicYear().getAcademicYearId());
            responseDto.setEducationLevelId(g.getEducationLevel().getEducationLevelId());
            grd.add(responseDto);
        }
        return ResponseEntity.ok(grd);

    }
    public ResponseEntity<GradeResponseDto> registerGrade(GradeRequestDto gradeRequestDto) {
        return gradeService.addNewGrade(gradeRequestDto);
    }

}

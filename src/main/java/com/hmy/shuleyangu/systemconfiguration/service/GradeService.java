package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.GradeRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.GradeResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.*;
import com.hmy.shuleyangu.systemconfiguration.repository.GradeRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private RemarkService remarkService;
    @Autowired
    private EducationLevelService educationLevelService;
    @Autowired
    private AcademicYearService academicYearService;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public List<Grades> findAllGrades(PageRequest pageRequest) {
        return gradeRepository.findAll(pageRequest).getContent();
    }

    public Optional<Grades> getGradeById(String gradeId){

        return gradeRepository.findById(gradeId);
    }

    public ResponseEntity<GradeResponseDto> addNewGrade(GradeRequestDto gradeDto) {
        Optional<Remarks> rmk = remarkService.getRemarkById(gradeDto.getRemarkId());
        Optional<AcademicYear> acy = academicYearService.getAcademicYearById(gradeDto.getAcademicYearId());
        Optional<EducationLevel> edl = educationLevelService.getEducationLevelById(gradeDto.getEducationLevelId());

        Grades g = new Grades();
        Remarks r = rmk.get();
        AcademicYear a = acy.get();
        EducationLevel e = edl.get();

        g.setRemarks(r);
        g.setAcademicYear(a);
        g.setEducationLevel(e);
        g.setGradeName(gradeDto.getGradeName());
        g.setGradeMinMark(gradeDto.getGradeMinMark());
        g.setGradeMaxMark(gradeDto.getGradeMaxMark());
        g.setGradePoint(gradeDto.getGradePoint());
        g.setStatus(gradeDto.getStatus());
        gradeRepository.save(g);

        GradeResponseDto responseDto = new GradeResponseDto();
        responseDto.setGradeId(g.getGradeId());
        responseDto.setGradeName(g.getGradeName());
        responseDto.setGradeMinMark(g.getGradeMinMark());
        responseDto.setGradeMaxMark(g.getGradeMaxMark());
        responseDto.setGradePoint(g.getGradePoint());
        responseDto.setCreatedDate(g.getCreatedDate());
        responseDto.setCreatedBy(g.getCreatedBy());
        responseDto.setModifiedDate(g.getModifiedDate());
        responseDto.setModifiedBy(g.getModifiedBy());
        responseDto.setRemarkId(r.getRemarkId());
        responseDto.setAcademicYearId(a.getAcademicYearId());
        responseDto.setEducationLevelId(e.getEducationLevelId());
        return  ResponseEntity.ok(responseDto);
    }


}

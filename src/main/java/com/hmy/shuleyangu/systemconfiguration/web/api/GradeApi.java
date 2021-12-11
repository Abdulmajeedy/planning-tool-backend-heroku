package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.GradeRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.GradeResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/grades")
public interface GradeApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<GradeResponseDto>> getGrades(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);


    @PostMapping(path = "/")
    public ResponseEntity<GradeResponseDto> registerGrade(@RequestBody GradeRequestDto gradeRequestDto);

}

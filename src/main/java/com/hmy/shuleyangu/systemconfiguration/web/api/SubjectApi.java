package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SubjectRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SubjectResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/subject")
public interface SubjectApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);


    @PostMapping(path = "/")
   public ResponseEntity<SubjectResponseDto> registerNewSubject(@RequestBody SubjectRequestDto subjectRequestDto);

    @GetMapping(path = "/{subjectId}")
    public ResponseEntity<SubjectResponseDto> getSubjectById(@PathVariable String subjectId);

}

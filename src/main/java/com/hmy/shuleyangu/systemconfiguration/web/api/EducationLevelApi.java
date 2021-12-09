package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.DistrictResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.EducationLevelRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.EducationLevelResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("educationLevel")
public interface EducationLevelApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<EducationLevelResponseDto>> getEducationLevels(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<EducationLevelResponseDto> registerNewEducationLevels(@RequestBody EducationLevelRequestDto elr);

    @GetMapping(path = "/{educationLevelId}")
    public ResponseEntity<EducationLevelResponseDto> getEducationLevelById(@PathVariable String educationLevelId);

    @PutMapping(path = "/{educationLevelId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Education level has been updated")
    public void updateEducationLevel(@PathVariable("educationLevelId")String educationLevelId,
                               @RequestBody EducationLevel educationLevel);

}

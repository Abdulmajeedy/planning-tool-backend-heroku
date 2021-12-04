package com.hmy.shuleyangu.systemconfiguration.web.api;


import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/academicYear")
public interface AcademicYearApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<AcademicYearResponseDto>> getAcademicYears(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<AcademicYearResponseDto> registerNewAcademicYear(@RequestBody AcademicYearRequestDto academicYear);

    @RequestMapping(value = "/{academicYearId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<AcademicYearResponseDto> getAcademicYearById(@PathVariable String academicYearId);

    @DeleteMapping(path = "/{academicYearId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
    public void deleteById(@PathVariable("academicYearId")String academicYearId);

    @RequestMapping(value = "/{academicYearId}", method = RequestMethod.PUT,produces = "application/json",consumes="application/json")
    public ResponseEntity updateAcademicYear(@PathVariable("academicYearId")String academicYearId, @RequestBody AcademicYear yearToUpdate);

}




package com.hmy.shuleyangu.systemconfiguration.web.api;


import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RequestMapping("/academicYear")
public interface AcademicYearApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<AcademicYearResponseDto>> getAcademicYears(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Zone Created")
    public void registerNewAcademicYear(@RequestBody AcademicYearRequestDto academicYear);

    @RequestMapping(value = "/{academicYearId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<AcademicYearResponseDto> getAcademicYearById(@PathVariable String zoneId);

    @DeleteMapping(path = "/{academicYearId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
    public void deleteById(@PathVariable("academicYearId")String academicYearId);

    @RequestMapping(value = "/{academicYearId}", method = RequestMethod.PUT,produces = "application/json",consumes="application/json")
    public ResponseEntity updateAcademicYear(@PathVariable("academicYearId")String academicYearId, @RequestBody AcademicYearResponseDto yearToUpdate);

}




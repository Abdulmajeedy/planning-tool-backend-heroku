package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.SpecializationRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.SpecializationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/teacherSpecialization")
public interface SpecializationApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<SpecializationResponseDto>> getSpecialization(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<SpecializationResponseDto> registerNewSpecialization(@RequestBody SpecializationRequestDto specializationRequestDto);

    @RequestMapping(value = "/{specializationId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<SpecializationResponseDto> getSpecializationById(@PathVariable String specializationId);

    @DeleteMapping(path = "/{specializationId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Specialization Deleted")
    public void deleteById(@PathVariable("specializationId")String specializationId);


}




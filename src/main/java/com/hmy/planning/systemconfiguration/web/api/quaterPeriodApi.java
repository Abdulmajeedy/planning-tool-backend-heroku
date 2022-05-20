package com.hmy.planning.systemconfiguration.web.api;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.quaterPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.quaterPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin
@RequestMapping("/quater")
public interface quaterPeriodApi {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<quaterPeriodResponseDto>> getQuaterPeriod(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<quaterPeriodResponseDto> registerNewQuaterPeriod(
            @RequestBody quaterPeriodRequestDto reqQuaterPeriod);

    @RequestMapping(value = "/{quaterPeriodCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<quaterPeriodResponseDto> getQuaterPeriodById(@PathVariable String reqQuaterPeriod);

    @DeleteMapping(path = "/{quaterPeriodCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Project Deleted")
    public void deleteById(@PathVariable("roleCode") String quaterPeriodCode);

    @RequestMapping(value = "/{quaterPeriodCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateQuaterPeriod(@PathVariable("quaterPeriodCode") String quaterPeriodCode,
            @RequestBody QuaterPeriod reqQuaterPeriod);

}

package com.hmy.planning.systemconfiguration.web.api;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.activityRequestDto;
import com.hmy.planning.systemconfiguration.dto.activityResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin
@RequestMapping("/activity")
public interface activityApi {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<activityResponseDto>> getBudgetPeriod(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<activityResponseDto> registerNewBudgetPeriod(
            @RequestBody activityRequestDto budgetPeriod);

    @RequestMapping(value = "/{activityCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<activityResponseDto> getBudgetPeriodById(
            @PathVariable("activityCode") String activityCode);

    @DeleteMapping(path = "/{activityCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Bugeting Period Deleted")
    public void deleteById(@PathVariable("activityCode") String activityCode);

    @RequestMapping(value = "/{activityCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateBudgetPeriod(@PathVariable("activityCode") String activityCode,
            @RequestBody Activity activity);

}

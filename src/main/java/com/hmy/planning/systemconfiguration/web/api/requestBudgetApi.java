package com.hmy.planning.systemconfiguration.web.api;

import java.util.*;

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

import com.hmy.planning.systemconfiguration.dto.requestBudgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto;

@CrossOrigin
@RequestMapping("/requestBudget")
public interface requestBudgetApi {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<requestBudgetResponseDto>> getRequestBudge(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<requestBudgetResponseDto> registerRequestBudge(
            @RequestBody requestBudgetRequestDto requestBudget);

    @RequestMapping(value = "/{impPeriodCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<requestBudgetResponseDto> getRequestBudgetById(@PathVariable String impPeriodCode);

    @DeleteMapping(path = "/{requestBudgetCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "impPeriodCode   Deleted")
    public void deleteById(@PathVariable("requestBudgetCode") String impPeriodCode);

    @DeleteMapping(path = "delete/")
    @ResponseStatus(code = HttpStatus.OK, reason = "requestBudgetCode   Deleted")
    public void deleteAll();

    @RequestMapping(value = "/{requestBudgetCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateRequestBudge(@PathVariable("requestBudgetCode") String impPeriodCode,
            @RequestBody requestBudgetRequestDto requestBudget);

    @RequestMapping(value = "updateStatus/{requestBudgetCode}", method = RequestMethod.GET)
    public ResponseEntity updateStatus(@PathVariable("requestBudgetCode") String impPeriodCode);

}

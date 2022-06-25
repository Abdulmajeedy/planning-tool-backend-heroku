package com.hmy.planning.systemconfiguration.web.api;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import com.hmy.planning.systemconfiguration.dto.impPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.impPeriodResponseDto;

@CrossOrigin
@RequestMapping("/impPeriod")
public interface impPeriodApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<impPeriodResponseDto>> getimpPeriode(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<impPeriodResponseDto> registerNewimpPeriode(
                        @RequestBody impPeriodRequestDto impPeriode);

        @RequestMapping(value = "/{impPeriodCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<impPeriodResponseDto> getimpPeriodeById(@PathVariable String impPeriodCode);

        @DeleteMapping(path = "/{impPeriodCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "impPeriodCode   Deleted")
        public void deleteById(@PathVariable("impPeriodCode") String impPeriodCode);

        @DeleteMapping(path = "delete/")
        @ResponseStatus(code = HttpStatus.OK, reason = "impPeriodCode   Deleted")
        public void deleteAll();

        @RequestMapping(value = "/{impPeriodCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateimpPeriode(@PathVariable("objectiveCode") String impPeriodCode,
                        @RequestBody impPeriodRequestDto objectives);

        @RequestMapping(value = "updateStatus/{impPeriodCode}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("impPeriodCode") String impPeriodCode);

}

package com.hmy.planning.systemconfiguration.web.api;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.strategiesRequestDto;
import com.hmy.planning.systemconfiguration.dto.strategiesResponseDto;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.models.projects;

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
@RequestMapping("/Strategy")
public interface strategiesApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<strategiesResponseDto>> getStartegy(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<strategiesResponseDto> registerNewStartegy(
                        @RequestBody strategiesRequestDto reqStrutegies);

        @RequestMapping(value = "/{strategyCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<strategiesResponseDto> getStartegyById(@PathVariable String strategyCode);

        @DeleteMapping(path = "/{strategyCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Project Deleted")
        public void deleteById(@PathVariable("strategyCode") String strategyCode);

        @RequestMapping(value = "/{strategyCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateStartegy(@PathVariable("strategyCode") String strategyCode,
                        @RequestBody Strategies reqStrategies);

        @RequestMapping(value = "updateStatus/{strategyCode}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("strategyCode") String strategyCode);

}

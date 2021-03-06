package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.activityQuaterPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.ActivityQuaterPeriod;

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
@RequestMapping("/activityQuaterPeriod")
public interface ActivityQuaterPeriodApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<activityQuaterPeriodResponseDto>> getActivityQuaterPeriod(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        // @RequestMapping(value = "/", method = RequestMethod.POST, produces =
        // "application/json")
        // public ResponseEntity<activityResponseDto> registerNewActivity(
        // @RequestBody activityRequestDto budgetPeriod);

        @RequestMapping(value = "/{activityQuaterPeriodCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<activityQuaterPeriodResponseDto> getgetActivityQuaterPeriodById(
                        @PathVariable("activityQuaterPeriodCode") String activityQuaterPeriodCode);

        @DeleteMapping(path = "/{activityQuaterPeriodCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "activityQuater Period Deleted")
        public void deleteById(@PathVariable("activityQuaterPeriodCode") String activityQuaterPeriodCode);

        @RequestMapping(value = "/{activityCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updategetActivityQuaterPeriod(@PathVariable("activityCode") String activityCode,
                        @RequestBody ActivityQuaterPeriod activityQuaterPeriod);

        @RequestMapping(value = "updateStatus/{activityQuaterPeriod}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("activityQuaterPeriod") String activityQuaterPeriod);

}

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

import com.hmy.planning.systemconfiguration.dto.staffRequestDto;
import com.hmy.planning.systemconfiguration.dto.staffResponseDto;
import com.hmy.planning.systemconfiguration.models.staff;

@CrossOrigin
@RequestMapping("/staff")
public interface staffApi {
        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<staffResponseDto>> getStaff(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<staffResponseDto> registerNewStaff(
                        @RequestBody staffRequestDto budgetPeriod);

        @RequestMapping(value = "/{staffID}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<staffResponseDto> getStaffById(
                        @PathVariable("staffID") String staffID);

        @DeleteMapping(path = "/{staffID}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Bugeting Period Deleted")
        public void deleteById(@PathVariable("staffID") String staffID);

        @RequestMapping(value = "/{staffID}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateStaff(@PathVariable("staffID") String staffID,
                        @RequestBody staff activity);

        @RequestMapping(value = "updateStatus/{staffID}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("staffID") String staffID);

        @RequestMapping(value = "count/", method = RequestMethod.GET)
        public ResponseEntity Count();

        // @RequestMapping(value = "getActivities/", method = RequestMethod.GET)
        // public ResponseEntity getActivities();

}

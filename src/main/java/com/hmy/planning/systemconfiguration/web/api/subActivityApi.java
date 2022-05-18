package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.subactivityRequestDto;
import com.hmy.planning.systemconfiguration.dto.subactivityResponseDto;
import com.hmy.planning.systemconfiguration.models.SubActivity;

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
@RequestMapping("/subActivity")
public interface subActivityApi {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<subactivityResponseDto>> getSubActivity(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<subactivityResponseDto> registerNewSubActivity(
            @RequestBody subactivityRequestDto reqSubActivity);

    @RequestMapping(value = "/{subactivityCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<subactivityResponseDto> getSubActivityById(@PathVariable String subactivityCode);

    @DeleteMapping(path = "/{subactivityCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Project Deleted")
    public void deleteById(@PathVariable("roleCode") String subactivityCode);

    @RequestMapping(value = "/{subactivityCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateSubActivity(@PathVariable("projectCode") String subactivityCode,
            @RequestBody SubActivity reqSubActivity);

}

package com.hmy.planning.systemconfiguration.web.api;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.objectiveRequestDto;
import com.hmy.planning.systemconfiguration.dto.objectiveResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;

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
@RequestMapping("/objective")
public interface objectiveApi {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<objectiveResponseDto>> getObjective(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<objectiveResponseDto> registerNewObjective(
            @RequestBody objectiveRequestDto objectives);

    @RequestMapping(value = "/{objectiveCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<objectiveResponseDto> getObjectiveById(@PathVariable String objectiveCode);

    @DeleteMapping(path = "/{objectiveCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
    public void deleteById(@PathVariable("objectiveCode") String objectiveCode);

    @RequestMapping(value = "/{objectiveCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateObjective(@PathVariable("objectiveCode") String objectiveCode,
            @RequestBody Objectives objectives);

}

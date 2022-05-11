package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.projectRequestDto;
import com.hmy.planning.systemconfiguration.dto.projectResponseDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
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
@RequestMapping("/projects")
public interface projectApi {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<projectResponseDto>> getProject(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<projectResponseDto> registerNewProject(
            @RequestBody projectRequestDto reqProject);

    @RequestMapping(value = "/{projectCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<projectResponseDto> getProjectById(@PathVariable String projectCode);

    @DeleteMapping(path = "/{projectCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Project Deleted")
    public void deleteById(@PathVariable("roleCode") String projectCode);

    @RequestMapping(value = "/{projectCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateProject(@PathVariable("projectCode") String projectCode,
            @RequestBody projects reqProject);

}

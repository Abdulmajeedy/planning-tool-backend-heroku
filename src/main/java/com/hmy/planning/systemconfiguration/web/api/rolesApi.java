package com.hmy.planning.systemconfiguration.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.roles;

@CrossOrigin
@RequestMapping("/academicYear")
public interface rolesApi {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<roleResponseDto>> getRole(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<roleResponseDto> registerNewRole(
            @RequestBody roleRequestDto academicYear);

    @RequestMapping(value = "/{roleCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<roleResponseDto> getRoleById(@PathVariable String academicYearId);

    @DeleteMapping(path = "/{roleCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
    public void deleteById(@PathVariable("roleCode") String academicYearId);

    @RequestMapping(value = "/{roleCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateRole(@PathVariable("roleCode") String academicYearId,
            @RequestBody roles reqRoles);

}

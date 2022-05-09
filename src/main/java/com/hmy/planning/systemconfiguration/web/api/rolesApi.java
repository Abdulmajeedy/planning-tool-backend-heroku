package com.hmy.planning.systemconfiguration.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.roles;

@CrossOrigin
@RequestMapping("/roles")
public interface rolesApi {
        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<roleResponseDto>> getRole(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<roleResponseDto> registerNewRole(
                        @RequestBody roleRequestDto role);

        @RequestMapping(value = "/{roleCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<roleResponseDto> getRoleById(@PathVariable String roleCode);

        @DeleteMapping(path = "/{roleCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
        public void deleteById(@PathVariable("roleCode") String roleCode);

        @RequestMapping(value = "/{roleCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateRole(@PathVariable("roleCode") String roleCode,
                        @RequestBody roles reqRoles);

}

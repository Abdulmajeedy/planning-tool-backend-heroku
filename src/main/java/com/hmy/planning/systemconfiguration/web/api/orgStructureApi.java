package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.orgStructureRequestDto;
import com.hmy.planning.systemconfiguration.dto.orgStructureResponseDto;
import com.hmy.planning.systemconfiguration.models.orgStructure;

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
@RequestMapping("/orgStructure")
public interface orgStructureApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<orgStructureResponseDto>> getOrgStructure(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<orgStructureResponseDto> registerNewOrgStructure(
                        @RequestBody orgStructureRequestDto orgStructure);

        @RequestMapping(value = "/{officeID}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<orgStructureResponseDto> getOrgStructureById(@PathVariable String officeID);

        @DeleteMapping(path = "/{officeID}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
        public void deleteById(@PathVariable("officeID") String officeID);

        @RequestMapping(value = "updateStatus/{officeID}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("officeID") String officeID);

        @RequestMapping(value = "/{officeID}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateOrgStructure(@PathVariable("officeID") String officeID,
                        @RequestBody orgStructure orgStructure);

}
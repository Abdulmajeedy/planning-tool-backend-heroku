package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.targetRequestDto;
import com.hmy.planning.systemconfiguration.dto.targetResponseDto;
import com.hmy.planning.systemconfiguration.models.Target;

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
@RequestMapping("/target")
public interface targetApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<targetResponseDto>> getTarget(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<targetResponseDto> registerNewTarget(
                        @RequestBody targetRequestDto budgetPeriod);

        @RequestMapping(value = "/{targetCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<targetResponseDto> getTargetById(
                        @PathVariable("targetCode") String targetCode);

        @DeleteMapping(path = "/{targetCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "    Target Deleted")
        public void deleteById(@PathVariable("targetCode") String targetCode);

        @RequestMapping(value = "/{targetCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateTarget(@PathVariable("targetCode") String targetCode,
                        @RequestBody Target target);

        @RequestMapping(value = "updateStatus/{targetCode}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("targetCode") String targetCode);

}

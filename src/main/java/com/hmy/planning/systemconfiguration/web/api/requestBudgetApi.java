package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

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

import com.hmy.planning.systemconfiguration.dto.requestBudgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto;

@CrossOrigin
@RequestMapping("/requestBudget")
public interface requestBudgetApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<requestBudgetResponseDto>> getrequestBudget(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<requestBudgetResponseDto> registerNewrequestBudget(
                        @RequestBody requestBudgetRequestDto budgetPeriod);

        @RequestMapping(value = "/{requestBudgetCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<requestBudgetResponseDto> getrequestBudgetById(
                        @PathVariable("requestBudgetCode") String requestBudgetCode);

        @DeleteMapping(path = "/{requestBudgetCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Bugeting Period Deleted")
        public void deleteById(@PathVariable("requestBudgetCode") String requestBudgetCode);

        @RequestMapping(value = "/{requestBudgetCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updaterequestBudget(@PathVariable("requestBudgetCode") String requestBudgetCode,
                        @RequestBody requestBudgetRequestDto budgetPeriod);

        @RequestMapping(value = "updateStatus/{requestBudgetCode}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("requestBudgetCode") String requestBudgetCode);

        @RequestMapping(value = "getRequestBudgetByOffice/{officeID}", method = RequestMethod.GET)
        public ResponseEntity getRequestBudgetByOffice(@PathVariable("officeID") String officeID);

}

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

import com.hmy.planning.systemconfiguration.dto.budgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetResponseDto;

@CrossOrigin
@RequestMapping("/budget")
public interface budgetApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<budgetResponseDto>> getBudget(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<budgetResponseDto> registerNewBudget(
                        @RequestBody budgetRequestDto budget);

        @RequestMapping(value = "/{budgetCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<budgetResponseDto> getBudgetById(@PathVariable String budgetCode);

        @RequestMapping(value = "approveBudget/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<budgetResponseDto> ApproveActivity(
                        @RequestBody budgetRequestDto budget);

        @DeleteMapping(path = "/{budgetCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Academic year Deleted")
        public void deleteById(@PathVariable("budgetCode") String budgetCode);

        @RequestMapping(value = "/{budgetCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateBudget(@PathVariable("budgetCode") String budgetCode,
                        @RequestBody budgetRequestDto reqRoles);

        @RequestMapping(value = "updateStatus/{budgetCode}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("budgetCode") String budgetCode);

        @RequestMapping(value = "getBudgetByOffice/{OfficeID}", method = RequestMethod.GET)
        public ResponseEntity getApprovedBudgetByOffice(@PathVariable("OfficeID") String OfficeID);

}

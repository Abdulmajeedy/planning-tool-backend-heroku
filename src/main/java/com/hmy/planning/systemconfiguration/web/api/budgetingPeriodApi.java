package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.budgetPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;

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
@RequestMapping("/budget-Period")
public interface budgetingPeriodApi {

        @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<budgetPeriodResponseDto>> getBudgetPeriod(
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int size);

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<budgetPeriodResponseDto> registerNewBudgetPeriod(
                        @RequestBody budgetPeriodRequestDto budgetPeriod);

        @RequestMapping(value = "/{budgetYearCode}", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<budgetPeriodResponseDto> getBudgetPeriodById(
                        @PathVariable("budgetYearCode") String budgetYearCode);

        @DeleteMapping(path = "/{budgetYearCode}")
        @ResponseStatus(code = HttpStatus.OK, reason = "Bugeting Period Deleted")
        public void deleteById(@PathVariable("budgetYearCode") String budgetYearCode);

        @RequestMapping(value = "/{budgetYearCode}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
        public ResponseEntity updateBudgetPeriod(@PathVariable("budgetYearCode") String budgetYearCode,
                        @RequestBody budgetingPeriod budgetPeriod);

        @RequestMapping(value = "updateStatus/{budgetYearCode}", method = RequestMethod.GET)
        public ResponseEntity updateStatus(@PathVariable("budgetYearCode") String budgetYearCode);

}

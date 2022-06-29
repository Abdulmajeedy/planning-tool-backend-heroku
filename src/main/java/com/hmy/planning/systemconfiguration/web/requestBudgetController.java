package com.hmy.planning.systemconfiguration.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hmy.planning.systemconfiguration.dto.requestBudgetRequestDto;
import com.hmy.planning.systemconfiguration.service.requestBudgetService;
import com.hmy.planning.systemconfiguration.web.api.requestBudgetApi;

@RestController

public class requestBudgetController implements requestBudgetApi {

    private ModelMapper modelMapper;
    private requestBudgetService reqBudgetService;

    @Autowired
    public requestBudgetController(requestBudgetService reqBudgetService, ModelMapper modelMapper) {

        this.reqBudgetService = reqBudgetService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto>> getrequestBudget(
            int page, int size) {
        return ResponseEntity.ok(reqBudgetService.findAllrequestBudget(page, size));
    }

    @Override
    public ResponseEntity<com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto> registerNewrequestBudget(
            requestBudgetRequestDto budgetPeriod) {
        return reqBudgetService.addNewrequestBudget(budgetPeriod);
    }

    @Override
    public ResponseEntity<com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto> getrequestBudgetById(
            String requestBudgetCode) {
        return ResponseEntity.ok(reqBudgetService.getrequestBudgetById(requestBudgetCode));

    }

    @Override
    public void deleteById(String requestBudgetCode) {
        reqBudgetService.deleterequestBudget(requestBudgetCode);

    }

    @Override
    public ResponseEntity updaterequestBudget(String requestBudgetCode, requestBudgetRequestDto budgetPeriod) {
        return ResponseEntity.ok(reqBudgetService.updaterequestBudget(requestBudgetCode, budgetPeriod));
    }

    @Override
    public ResponseEntity updateStatus(String requestBudgetCode) {
        return ResponseEntity.ok().body(reqBudgetService.updateStatus(requestBudgetCode));

    }

}

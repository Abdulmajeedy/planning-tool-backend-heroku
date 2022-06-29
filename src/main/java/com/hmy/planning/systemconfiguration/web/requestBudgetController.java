package com.hmy.planning.systemconfiguration.web;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hmy.planning.systemconfiguration.dto.requestBudgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto;
import com.hmy.planning.systemconfiguration.repository.RoleRepository;
import com.hmy.planning.systemconfiguration.service.requestBudgetService;
import com.hmy.planning.systemconfiguration.web.api.requestBudgetApi;

@RestController
public class requestBudgetController implements requestBudgetApi {

    private requestBudgetService requestBudgetService;
    private RoleRepository roleRepo;
    private ModelMapper modelMapper;

    @Autowired
    public requestBudgetController(requestBudgetService requestBudgetService, ModelMapper modelMapper) {

        this.requestBudgetService = requestBudgetService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<requestBudgetResponseDto> registerRequestBudget(requestBudgetRequestDto reqRequestBudget) {
        return requestBudgetService.addNewRequestBudget(reqRequestBudget);
    }

    @Override
    public ResponseEntity<requestBudgetResponseDto> getRequestBudgetById(String requestBudgetCode) {
        return ResponseEntity.ok(requestBudgetService.getRequestBudgetById(requestBudgetCode));
    }

    public void deleteById(String requestBudgetCode) {
        requestBudgetService.deleteRequestBudget(requestBudgetCode);
    }

    @Override
    public ResponseEntity updateStatus(String roleCode) {
        return ResponseEntity.ok().body(requestBudgetService.updateStatus(roleCode));
    }

    @Override
    public ResponseEntity<List<requestBudgetResponseDto>> getRequestBudge(int page, int size) {
        return ResponseEntity.ok(requestBudgetService.findAllRequestBudget(page, size));

    }

    @Override
    public void deleteAll() {
        requestBudgetService.deleteAllRequestBudget();
    }

    @Override
    public ResponseEntity<requestBudgetResponseDto> registerRequestBudge(requestBudgetRequestDto requestBudget) {
        return requestBudgetService.addNewRequestBudget(requestBudget);

    }

    @Override
    public ResponseEntity updateRequestBudge(String impPeriodCode, requestBudgetRequestDto requestBudget) {
        // TODO Auto-generated method stub
        return null;
    }
}

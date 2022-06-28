package com.hmy.planning.systemconfiguration.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hmy.planning.systemconfiguration.dto.budgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetResponseDto;
import com.hmy.planning.systemconfiguration.repository.BudgetRepository;
import com.hmy.planning.systemconfiguration.service.budgetService;
import com.hmy.planning.systemconfiguration.web.api.budgetApi;

@RestController
public class budgetController implements budgetApi {

    private budgetService budgService;
    private BudgetRepository budgetRepo;
    private ModelMapper modelMapper;

    @Autowired
    public budgetController(budgetService budgService, ModelMapper modelMapper) {

        this.budgService = budgService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<budgetResponseDto>> getBudget(int page, int size) {
        return ResponseEntity.ok(budgService.findAllBudget(page, size));
    }

    @Override
    public ResponseEntity<budgetResponseDto> registerNewBudget(budgetRequestDto reqBudget) {
        return budgService.addNewBudget(reqBudget);
    }

    @Override
    public ResponseEntity<budgetResponseDto> getBudgetById(String budgetCode) {
        return ResponseEntity.ok(budgService.getBudgetById(budgetCode));
    }

    @Override
    public void deleteById(String budgetCode) {
        budgService.deleteBudget(budgetCode);
    }

    @Override
    public ResponseEntity updateBudget(String budgetCode, budgetRequestDto reqBudgets) {
        return ResponseEntity.ok(budgService.updateBuget(budgetCode, reqBudgets));
    }

    @Override
    public ResponseEntity updateStatus(String budgetCode) {
        return ResponseEntity.ok().body(budgService.updateStatus(budgetCode));
    }

    @Override
    public ResponseEntity<budgetResponseDto> ApproveActivity(budgetRequestDto reqBudget) {
        return budgService.ApproveActivity(reqBudget);
    }

}

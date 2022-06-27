package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hmy.planning.systemconfiguration.dto.budgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetResponseDto;
import com.hmy.planning.systemconfiguration.models.Budget;
import com.hmy.planning.systemconfiguration.repository.BudgetRepository;

import lombok.Data;

@Service
@Data
public class budgetService {

    @Autowired
    private BudgetRepository budgetRepo;
    private final ModelMapper modelmapper;

    public List<budgetResponseDto> findAllBudget(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Budget> budgets = budgetRepo.findAll(pageRequest).getContent();
        List<budgetResponseDto> roleDto = new ArrayList<>();
        for (Budget bu : budgets) {
            budgetResponseDto responseDto = modelmapper.map(bu, budgetResponseDto.class);
            roleDto.add(responseDto);
        }
        return roleDto;
    }

    public ResponseEntity<budgetResponseDto> addNewBudget(budgetRequestDto reqBudget) {
        Budget budget = modelmapper.map(reqBudget, Budget.class);
        budgetRepo.save(budget);

        budgetResponseDto bu = modelmapper.map(budget, budgetResponseDto.class);
        return ResponseEntity.ok(bu);

    }

    public void deleteBudget(String budgetCode) {

        budgetRepo.deleteById(budgetCode);
    }

    public budgetResponseDto getBudgetById(String budgetCode) {
        Optional<Budget> budget = budgetRepo.findById(budgetCode);
        if (!budget.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + budgetCode + "is not Found");
        } else {
            Budget bu = budget.get();
            budgetResponseDto responseDto = modelmapper.map(bu, budgetResponseDto.class);
            return responseDto;
        }
    }

    public ResponseEntity<budgetResponseDto> updateBuget(String budgetCode, budgetRequestDto reqBudgets) {

        Optional<Budget> budget = budgetRepo.findById(budgetCode);
        if (!budget.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + budgetCode + "is not Found");
        }
        Budget rol = modelmapper.map(reqBudgets, Budget.class);
        rol.setBudgetCode(budgetCode);
        budgetRepo.save(rol);

        budgetResponseDto roltDto = modelmapper.map(rol, budgetResponseDto.class);
        return ResponseEntity.ok(roltDto);

    }

    public Map<String, Boolean> updateStatus(String budgetCode) {
        Optional<Budget> bp = budgetRepo.findById(budgetCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        budgetRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    public Optional<Budget> getBugetCode(String budgetCode) {
        return budgetRepo.findById(budgetCode);
    }

}

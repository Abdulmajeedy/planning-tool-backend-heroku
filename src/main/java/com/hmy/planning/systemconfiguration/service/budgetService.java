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
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.Budget;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;
import com.hmy.planning.systemconfiguration.repository.BudgetRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class budgetService {

    @Autowired
    private final BudgetRepository budgetRepo;
    private final ActivityRepository activityRepo;

    private final ModelMapper modelmapper;

    public List<budgetResponseDto> findAllBudget(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Budget> budgets = budgetRepo.findAll(pageRequest).getContent();
        List<budgetResponseDto> roleDto = new ArrayList<>();
        for (Budget bu : budgets) {
            budgetResponseDto responseDto = modelmapper.map(bu, budgetResponseDto.class);
            responseDto.setActivityCode(bu.getActivities().getActivityCode());
            roleDto.add(responseDto);
        }
        return roleDto;
    }

    public ResponseEntity<budgetResponseDto> addNewBudget(budgetRequestDto reqBudget) {
        log.info(reqBudget.toString());
        Optional<Activity> activity = activityRepo.findById(reqBudget.getActivityCode());

        Activity activityObj = new Activity();
        activityObj.setActivityCode(activity.get().getActivityCode());

        Budget budget = modelmapper.map(reqBudget, Budget.class);
        budget.setActivities(activityObj);
        budgetRepo.save(budget);

        budgetResponseDto bu = modelmapper.map(budget, budgetResponseDto.class);
        bu.setActivityCode(budget.getActivities().getActivityCode());
        return ResponseEntity.ok(bu);

    }

    public ResponseEntity<budgetResponseDto> ApproveActivity(budgetRequestDto reqBudget) {
        // log.info(reqBudget.toString());
        Optional<Activity> activity = activityRepo.findById(reqBudget.getActivityCode());

        Activity activityObj = new Activity();
        activityObj.setActivityCode(activity.get().getActivityCode());

        Budget budget = modelmapper.map(reqBudget, Budget.class);
        budget.setActivities(activityObj);
        budget.setStatus(1);
        budget.setIsCurrent(1);
        budgetRepo.save(budget);

        activityRepo.UpdateEditStatus(reqBudget.getActivityCode());

        budgetResponseDto bu = modelmapper.map(budget, budgetResponseDto.class);
        bu.setActivityCode(budget.getActivities().getActivityCode());
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
            responseDto.setActivityCode(bu.getActivities().getActivityCode());
            return responseDto;
        }
    }

    public ResponseEntity<budgetResponseDto> updateBuget(String budgetCode, budgetRequestDto reqBudgets) {

        Optional<Activity> activity = activityRepo.findById(reqBudgets.getActivityCode());
        if (!activity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + budgetCode + "is not Found");
        }

        Activity activityObj = new Activity();
        activityObj.setActivityCode(activity.get().getActivityCode());

        Budget budget = modelmapper.map(reqBudgets, Budget.class);
        budget.setBudgetCode(budgetCode);
        budget.setActivities(activityObj);
        budgetRepo.save(budget);

        budgetResponseDto bu = modelmapper.map(budget, budgetResponseDto.class);
        return ResponseEntity.ok(bu);
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

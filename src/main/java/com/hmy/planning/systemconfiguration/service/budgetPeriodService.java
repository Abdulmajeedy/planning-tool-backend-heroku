package com.hmy.planning.systemconfiguration.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.budgetPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.repository.BudgetPeriodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class budgetPeriodService {

    @Autowired
    private BudgetPeriodRepository budgetRepo;

    @Autowired
    public budgetPeriodService(BudgetPeriodRepository budgetRepo) {
        this.budgetRepo = budgetRepo;

    }

    public List<budgetingPeriod> findAllBudgetPeriod(PageRequest pageRequest) {
        return budgetRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<budgetPeriodResponseDto> addNewBudgetPeriod(budgetPeriodRequestDto reqBudgetPriod) {
        budgetingPeriod budgetPer = new budgetingPeriod();

        budgetPer.setYear(reqBudgetPriod.getYear());
        budgetPer.setStatus(reqBudgetPriod.getStatus());
        budgetRepo.save(budgetPer);

        budgetPeriodResponseDto budgetPeDto = new budgetPeriodResponseDto();
        budgetPeDto.setBudgetYearCode(budgetPer.getBudgetYearCode());
        budgetPeDto.setYear(budgetPer.getYear());
        budgetPeDto.setStatus(budgetPer.getStatus());
        budgetPeDto.setCreatedDate(budgetPer.getCreatedDate());
        budgetPeDto.setCreatedBy(budgetPer.getCreatedBy());
        budgetPeDto.setModifiedDate(budgetPer.getModifiedDate());
        budgetPeDto.setModifiedBy(budgetPer.getModifiedBy());
        return ResponseEntity.ok(budgetPeDto);
    }

    public void deleteBudgetPeriod(String officeID) {

        budgetRepo.deleteById(officeID);
    }

    public Optional<budgetingPeriod> getBudgetPeriodById(String budgetYearCode) {
        return budgetRepo.findById(budgetYearCode);
    }

    public void updateBudgetPeriod(String budgetYearCode, budgetingPeriod reqBudgetPeriod) {
        budgetRepo.findById(budgetYearCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Office  with ID " + budgetYearCode + " does not exist"));

        reqBudgetPeriod.setBudgetYearCode(budgetYearCode);
        budgetingPeriod budgetPer = new budgetingPeriod();
        budgetPer.setCreatedBy(reqBudgetPeriod.getCreatedBy());
        budgetPer.setCreatedDate(reqBudgetPeriod.getCreatedDate());
        budgetPer.setModifiedBy(reqBudgetPeriod.getModifiedBy());
        budgetRepo.save(reqBudgetPeriod);
    }

    public Optional<budgetingPeriod> getBudgetYearCode(String BudgetYearCode) {
        return budgetRepo.findById(BudgetYearCode);
    }

    public Map<String, Boolean> updateStatus(String BudgetYearCode) {
        Optional<budgetingPeriod> bp = budgetRepo.findById(BudgetYearCode);
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

}

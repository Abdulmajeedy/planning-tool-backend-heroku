package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.budgetPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.repository.BudgetPeriodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

}

package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.budgetPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.budgetPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.repository.BudgetPeriodRepository;
import com.hmy.planning.systemconfiguration.service.budgetPeriodService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.budgetingPeriodApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class budgetingPeriodController implements budgetingPeriodApi {

    private budgetPeriodService budgetPeriodServices;
    private BudgetPeriodRepository budgetPeriodRepo;
    private ModelMapper modelMapper;

    @Autowired
    public budgetingPeriodController(budgetPeriodService budgetPeriodServices, ModelMapper modelMapper) {

        this.budgetPeriodServices = budgetPeriodServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<budgetPeriodResponseDto>> getBudgetPeriod(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<budgetingPeriod> role = budgetPeriodServices.findAllBudgetPeriod(pageRequest);
        List<budgetPeriodResponseDto> roleDto = new ArrayList<>();
        for (budgetingPeriod rol : role) {
            budgetPeriodResponseDto responseDto = new budgetPeriodResponseDto();
            responseDto.setBudgetYearCode(rol.getBudgetYearCode());
            responseDto.setYear(rol.getYear());
            responseDto.setStatus(rol.getStatus());
            responseDto.setCreatedDate(rol.getCreatedDate());
            responseDto.setCreatedBy(rol.getCreatedBy());
            responseDto.setModifiedDate(rol.getModifiedDate());
            responseDto.setModifiedBy(rol.getModifiedBy());
            roleDto.add(responseDto);
        }
        return ResponseEntity.ok(roleDto);
    }

    @Override
    public ResponseEntity<budgetPeriodResponseDto> registerNewBudgetPeriod(budgetPeriodRequestDto budgetPeriod) {
        return budgetPeriodServices.addNewBudgetPeriod(budgetPeriod);
    }

    @Override
    public ResponseEntity<budgetPeriodResponseDto> getBudgetPeriodById(String BudgetYearCode) {
        Optional<budgetingPeriod> orgStr = budgetPeriodServices.getBudgetPeriodById(BudgetYearCode);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            budgetingPeriod bg = orgStr.get();
            budgetPeriodResponseDto responseDto = new budgetPeriodResponseDto();

            responseDto.setBudgetYearCode(bg.getBudgetYearCode());
            responseDto.setYear(bg.getYear());
            responseDto.setStatus(bg.getStatus());
            responseDto.setCreatedDate(bg.getCreatedDate());
            responseDto.setCreatedBy(bg.getCreatedBy());
            responseDto.setModifiedDate(bg.getModifiedDate());
            responseDto.setModifiedBy(bg.getModifiedBy());

            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String BudgetingYearCode) {
        budgetPeriodServices.deleteBudgetPeriod(BudgetingYearCode);

    }

    @Override
    public ResponseEntity updateBudgetPeriod(String budgetYearCode, budgetingPeriod budgetPeriod) {
        Optional<budgetingPeriod> budPer = budgetPeriodServices.getBudgetPeriodById(budgetYearCode);
        budgetPeriodServices.updateBudgetPeriod(budgetYearCode, budgetPeriod);
        budgetingPeriod budgetPer = budPer.get();
        budgetPeriodResponseDto responseDto = new budgetPeriodResponseDto();

        responseDto.setBudgetYearCode(budgetPer.getBudgetYearCode());
        responseDto.setYear(budgetPer.getYear());
        responseDto.setStatus(budgetPer.getStatus());
        responseDto.setCreatedDate(budgetPer.getCreatedDate());
        responseDto.setCreatedBy(budgetPer.getCreatedBy());
        responseDto.setModifiedDate(budgetPer.getModifiedDate());
        responseDto.setModifiedBy(budgetPer.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity updateStatus(String budgetYearCode) {
        return ResponseEntity.ok().body(budgetPeriodServices.updateStatus(budgetYearCode));

    }

}

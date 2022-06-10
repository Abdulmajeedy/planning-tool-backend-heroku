package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.strategiesRequestDto;
import com.hmy.planning.systemconfiguration.dto.strategiesResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.repository.StrategiesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StrategiesService {

    @Autowired
    private StrategiesRepository strategyRepo;

    @Autowired
    private objectiveService objectiveService;

    @Autowired
    public StrategiesService(StrategiesRepository strategyRepo, objectiveService objectiveService) {
        this.strategyRepo = strategyRepo;
        this.objectiveService = objectiveService;
    }

    public List<Strategies> findAllStrategies(PageRequest pageRequest) {
        return strategyRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<strategiesResponseDto> addNewStrategies(strategiesRequestDto reqStrategies) {
        Optional<Objectives> objective = objectiveService.getObjectiveCode(reqStrategies.getObjectiveCodes());
        System.out.println(objective.isPresent());
        if (!objective.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Objectives objectives = objective.get();
        Strategies str = new Strategies();
        str.setStrategy(reqStrategies.getStrategy());
        str.setObjectives(objectives);
        str.setStatus(reqStrategies.getStatus());
        strategyRepo.save(str);

        strategiesResponseDto strategyDto = new strategiesResponseDto();
        strategyDto.setStrategy(str.getStrategy());
        strategyDto.setObjectiveCode(str.getObjectives().getObjectiveCode());
        strategyDto.setStatus(str.getStatus());
        strategyDto.setCreatedDate(str.getCreatedDate());
        strategyDto.setCreatedBy(str.getCreatedBy());
        strategyDto.setModifiedDate(str.getModifiedDate());
        strategyDto.setModifiedBy(str.getModifiedBy());
        return ResponseEntity.ok(strategyDto);
    }

    public void deleteStrategies(String strategyCode) {

        strategyRepo.deleteById(strategyCode);
    }

    public Optional<Strategies> getStrategiesById(String budgetYearCode) {
        return strategyRepo.findById(budgetYearCode);
    }

    public void updateStrategies(String strategyCode, Strategies reqStrategies) {
        strategyRepo.findById(strategyCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Strategies  with Code " + strategyCode + " does not exist"));

        reqStrategies.setStrategyCode(strategyCode);
        Strategies budgetPer = new Strategies();
        budgetPer.setCreatedBy(reqStrategies.getCreatedBy());
        budgetPer.setCreatedDate(reqStrategies.getCreatedDate());
        budgetPer.setModifiedBy(reqStrategies.getModifiedBy());
        strategyRepo.save(reqStrategies);
    }

    public Optional<Strategies> getStrategiesCode(String strategyCode) {
        return strategyRepo.findById(strategyCode);
    }

    public Map<String, Boolean> updateStatus(String officeID) {
        Optional<Strategies> bp = strategyRepo.findById(officeID);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        strategyRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}
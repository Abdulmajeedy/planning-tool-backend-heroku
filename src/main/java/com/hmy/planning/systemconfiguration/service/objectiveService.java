package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.objectiveRequestDto;
import com.hmy.planning.systemconfiguration.dto.objectiveResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.repository.ObjectiveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class objectiveService {

    @Autowired
    private ObjectiveRepository objectiveRepo;

    private budgetPeriodService budgetPeriodService;

    @Autowired
    public objectiveService(ObjectiveRepository objectiveRepo, budgetPeriodService budgetPeriodService) {
        this.objectiveRepo = objectiveRepo;
        this.budgetPeriodService = budgetPeriodService;

    }

    public List<Objectives> findAllObjectives(PageRequest pageRequest) {
        return objectiveRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<objectiveResponseDto> addNewObjective(objectiveRequestDto reqObjective) {
        Optional<budgetingPeriod> budgetPeriod = budgetPeriodService
                .getBudgetYearCode(reqObjective.getBudgetYearCode());

        if (!budgetPeriod.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        budgetingPeriod budPeriod = budgetPeriod.get();
        Objectives objective = new Objectives();

        objective.setObjective(reqObjective.getObjective());
        objective.setBudgetingPeriod(budPeriod);
        objective.setStatus(reqObjective.getStatus());
        objectiveRepo.save(objective);

        objectiveResponseDto objectiveDto = new objectiveResponseDto();
        objectiveDto.setObjectiveCode(objective.getObjectiveCode());
        objectiveDto.setObjective(objective.getObjective());
        objective.setBudgetingPeriod(objective.getBudgetingPeriod());
        objectiveDto.setStatus(objective.getStatus());
        objectiveDto.setCreatedDate(objective.getCreatedDate());
        objectiveDto.setCreatedBy(objective.getCreatedBy());
        objectiveDto.setModifiedDate(objective.getModifiedDate());
        objectiveDto.setModifiedBy(objective.getModifiedBy());
        return ResponseEntity.ok(objectiveDto);
    }

    public void deleteObjective(String objectiveCode) {

        objectiveRepo.deleteById(objectiveCode);
    }

    public Optional<Objectives> getObjectiveById(String objectiveCode) {
        return objectiveRepo.findById(objectiveCode);
    }

    public void updateObjective(String objectiveCode, Objectives reqObjective) {
        objectiveRepo.findById(objectiveCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Bojective  with Code " + objectiveCode + " does not exist"));

        reqObjective.setObjectiveCode(objectiveCode);
        Objectives objectives = new Objectives();
        objectives.setCreatedBy(reqObjective.getCreatedBy());
        objectives.setCreatedDate(reqObjective.getCreatedDate());
        objectives.setModifiedBy(reqObjective.getModifiedBy());
        objectiveRepo.save(reqObjective);
    }

    public Optional<Objectives> getObjectiveCode(String ObjectiveCode) {
        return objectiveRepo.findById(ObjectiveCode);
    }

    public Map<String, Boolean> updateStatus(String ObjectiveCode) {
        Optional<Objectives> bp = objectiveRepo.findById(ObjectiveCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        objectiveRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

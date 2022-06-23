package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.objectiveRequestDto;
import com.hmy.planning.systemconfiguration.dto.objectiveResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.repository.BudgetPeriodRepository;
import com.hmy.planning.systemconfiguration.repository.ObjectiveRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class objectiveService {

    private static final Object Objectives = null;
    @Autowired
    private ObjectiveRepository objectiveRepo;
    private final BudgetPeriodRepository budgetPeriodRepo;
    private final ModelMapper modelmapper;
    private budgetPeriodService budgetPeriodService;

    public List<objectiveResponseDto> findAllObjectives(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Objectives> obj = objectiveRepo.findAll(pageRequest).getContent();
        List<objectiveResponseDto> objDto = new ArrayList<>();
        for (Objectives objective : obj) {
            objectiveResponseDto responseDto = modelmapper.map(objective, objectiveResponseDto.class);
            responseDto.setBudgetYearCode(objective.getBudgetingPeriod().getBudgetYearCode());
            objDto.add(responseDto);
        }
        return objDto;
    }

    public ResponseEntity<objectiveResponseDto> addNewObjective(objectiveRequestDto reqObjective) {
        Optional<budgetingPeriod> budgetPeriod = budgetPeriodRepo.findById(reqObjective.getBudgetYearCode());

        budgetingPeriod budgetObj = new budgetingPeriod();
        budgetObj.setBudgetYearCode(budgetPeriod.get().getBudgetYearCode());

        Objectives objtvs = modelmapper.map(reqObjective, Objectives.class);
        objtvs.setBudgetingPeriod(budgetObj);
        objectiveRepo.save(objtvs);

        objectiveResponseDto obj = modelmapper.map(objtvs, objectiveResponseDto.class);
        return ResponseEntity.ok(obj);
    }

    public void deleteObjective(String objectiveCode) {

        objectiveRepo.deleteById(objectiveCode);
    }

    public objectiveResponseDto getObjectiveById(String objectiveCode) {
        Optional<Objectives> office = objectiveRepo.findById(objectiveCode);
        if (!office.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + objectiveCode + "is not Found");
        } else {
            Objectives obj = office.get();
            objectiveResponseDto responseDto = modelmapper.map(obj, objectiveResponseDto.class);
            responseDto.setBudgetYearCode(obj.getBudgetingPeriod().getBudgetYearCode());
            return responseDto;
        }
    }

    public ResponseEntity<objectiveResponseDto> updateObjective(String objectiveCode, objectiveRequestDto reqObjective) {
        Optional<Objectives> objective = objectiveRepo.findById(objectiveCode);
        if (!objective.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + objectiveCode + "is not Found");
        }
        Objectives obctc = modelmapper.map(reqObjective, Objectives.class);
        obctc.setObjectiveCode(objectiveCode);
        objectiveRepo.save(obctc);

        objectiveResponseDto roltDto = modelmapper.map(obctc, objectiveResponseDto.class);
        return ResponseEntity.ok(roltDto);

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

package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.objectiveRequestDto;
import com.hmy.planning.systemconfiguration.dto.objectiveResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;
import com.hmy.planning.systemconfiguration.repository.ObjectiveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class objectiveService {

    @Autowired
    private ObjectiveRepository objectiveRepo;

    @Autowired
    public objectiveService(ObjectiveRepository objectiveRepo) {
        this.objectiveRepo = objectiveRepo;

    }

    public List<Objectives> findAllObjectives(PageRequest pageRequest) {
        return objectiveRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<objectiveResponseDto> addNewObjective(objectiveRequestDto reqObjective) {
        Objectives objective = new Objectives();

        objective.setObjective(reqObjective.getObjective());
        objective.setStatus(reqObjective.getStatus());
        objectiveRepo.save(objective);

        objectiveResponseDto objectiveDto = new objectiveResponseDto();
        objectiveDto.setObjective(objective.getObjective());
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
                        "Office  with ID " + objectiveCode + " does not exist"));

        reqObjective.setObjectiveCode(objectiveCode);
        Objectives objectives = new Objectives();
        objectives.setCreatedBy(reqObjective.getCreatedBy());
        objectives.setCreatedDate(reqObjective.getCreatedDate());
        objectives.setModifiedBy(reqObjective.getModifiedBy());
        objectiveRepo.save(reqObjective);
    }

}

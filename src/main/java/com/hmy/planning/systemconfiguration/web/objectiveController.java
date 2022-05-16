package com.hmy.planning.systemconfiguration.web;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.objectiveRequestDto;
import com.hmy.planning.systemconfiguration.dto.objectiveResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;
import com.hmy.planning.systemconfiguration.repository.ObjectiveRepository;
import com.hmy.planning.systemconfiguration.service.objectiveService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.objectiveApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class objectiveController implements objectiveApi {

    private objectiveService objectiveService;
    private ObjectiveRepository objectiveRepo;
    private ModelMapper modelMapper;

    @Autowired
    public objectiveController(objectiveService objectiveService, ModelMapper modelMapper) {

        this.objectiveService = objectiveService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<objectiveResponseDto>> getObjective(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Objectives> objective = objectiveService.findAllObjectives(pageRequest);
        List<objectiveResponseDto> objDto = new ArrayList<>();
        for (Objectives object : objective) {
            objectiveResponseDto responseDto = new objectiveResponseDto();
            responseDto.setObjectiveCode(object.getObjectiveCode());
            responseDto.setObjective(object.getObjective());
            responseDto.setBudgetYearCode(object.getBudgetingPeriod().getBudgetYearCode());
            responseDto.setStatus(object.getStatus());
            responseDto.setCreatedDate(object.getCreatedDate());
            responseDto.setCreatedBy(object.getCreatedBy());
            responseDto.setModifiedDate(object.getModifiedDate());
            responseDto.setModifiedBy(object.getModifiedBy());
            objDto.add(responseDto);
        }
        return ResponseEntity.ok(objDto);
    }

    @Override
    public ResponseEntity<objectiveResponseDto> registerNewObjective(objectiveRequestDto objectives) {
        return objectiveService.addNewObjective(objectives);
    }

    @Override
    public ResponseEntity<objectiveResponseDto> getObjectiveById(String objectiveCode) {
        Optional<Objectives> objective = objectiveService.getObjectiveById(objectiveCode);
        if (!objective.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            Objectives obje = objective.get();
            objectiveResponseDto responseDto = new objectiveResponseDto();
            responseDto.setObjectiveCode(obje.getObjectiveCode());
            responseDto.setObjective(obje.getObjective());
            responseDto.setStatus(obje.getStatus());
            responseDto.setCreatedDate(obje.getCreatedDate());
            responseDto.setCreatedBy(obje.getCreatedBy());
            responseDto.setModifiedDate(obje.getModifiedDate());
            responseDto.setModifiedBy(obje.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String objectiveCode) {
        objectiveService.deleteObjective(objectiveCode);
    }

    @Override
    public ResponseEntity updateObjective(String objectiveCode, Objectives objectives) {
        Optional<Objectives> objective = objectiveService.getObjectiveById(objectiveCode);
        objectiveService.updateObjective(objectiveCode, objectives);
        Objectives obje = objective.get();
        objectiveResponseDto responseDto = new objectiveResponseDto();
        responseDto.setObjectiveCode(obje.getObjectiveCode());
        responseDto.setObjectiveCode(obje.getObjective());
        responseDto.setStatus(obje.getStatus());
        responseDto.setCreatedDate(obje.getCreatedDate());
        responseDto.setCreatedBy(obje.getCreatedBy());
        responseDto.setModifiedDate(obje.getModifiedDate());
        responseDto.setModifiedBy(obje.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}

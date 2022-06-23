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
        return ResponseEntity.ok(objectiveService.findAllObjectives(page, size));
    }

    @Override
    public ResponseEntity<objectiveResponseDto> registerNewObjective(objectiveRequestDto objectives) {
        return objectiveService.addNewObjective(objectives);
    }

    @Override
    public ResponseEntity<objectiveResponseDto> getObjectiveById(String objectiveCode) {
        return ResponseEntity.ok(objectiveService.getObjectiveById(objectiveCode));
    }

    @Override
    public void deleteById(String objectiveCode) {
        objectiveService.deleteObjective(objectiveCode);
    }

    @Override
    public void deleteAll() {
        objectiveService.deleteAllObjectives();
    }

    @Override
    public ResponseEntity<objectiveResponseDto> updateObjective(String objectiveCode, objectiveRequestDto objectives) {
        return objectiveService.updateObjective(objectiveCode, objectives);
    }

    @Override
    public ResponseEntity updateStatus(String objectiveCode) {
        return ResponseEntity.ok().body(objectiveService.updateStatus(objectiveCode));
    }

}

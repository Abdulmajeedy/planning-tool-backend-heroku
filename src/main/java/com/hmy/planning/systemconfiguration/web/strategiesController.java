package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.strategiesRequestDto;
import com.hmy.planning.systemconfiguration.dto.strategiesResponseDto;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.repository.ObjectiveRepository;
import com.hmy.planning.systemconfiguration.service.strategiesService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.strategiesApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class strategiesController implements strategiesApi {

    private strategiesService strategiesService;
    private ObjectiveRepository objectiveRepo;
    private ModelMapper modelMapper;

    @Autowired
    public strategiesController(strategiesService strategiesService, ModelMapper modelMapper) {

        this.strategiesService = strategiesService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<strategiesResponseDto>> getStartegy(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Strategies> strategies = strategiesService.findAllStrategies(pageRequest);
        List<strategiesResponseDto> straDto = new ArrayList<>();
        for (Strategies str : strategies) {
            strategiesResponseDto responseDto = new strategiesResponseDto();
            responseDto.setStrategyCode(str.getStrategyCode());
            responseDto.setStrategy(str.getStrategy());
            responseDto.setObjectiveCode(str.getObjectives().getObjectiveCode());
            responseDto.setStatus(str.getStatus());
            responseDto.setStatus(str.getStatus());
            responseDto.setCreatedDate(str.getCreatedDate());
            responseDto.setCreatedBy(str.getCreatedBy());
            responseDto.setModifiedDate(str.getModifiedDate());
            responseDto.setModifiedBy(str.getModifiedBy());
            straDto.add(responseDto);
        }
        return ResponseEntity.ok(straDto);
    }

    @Override
    public ResponseEntity<strategiesResponseDto> registerNewStartegy(strategiesRequestDto reqStrategies) {
        return strategiesService.addNewStrategies(reqStrategies);
    }

    @Override
    public ResponseEntity<strategiesResponseDto> getStartegyById(String strategyCode) {
        Optional<Strategies> strategies = strategiesService.getStrategiesById(strategyCode);
        if (!strategies.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            Strategies str = strategies.get();
            strategiesResponseDto responseDto = new strategiesResponseDto();

            responseDto.setStrategyCode(str.getStrategyCode());
            responseDto.setStrategy(str.getStrategy());
            responseDto.setObjectiveCode(str.getObjectives().getObjectiveCode());
            responseDto.setStatus(str.getStatus());
            responseDto.setCreatedDate(str.getCreatedDate());
            responseDto.setCreatedBy(str.getCreatedBy());
            responseDto.setModifiedDate(str.getModifiedDate());
            responseDto.setModifiedBy(str.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String strategyCode) {
        strategiesService.deleteStrategies(strategyCode);

    }

    @Override
    public ResponseEntity updateStartegy(String strategyCode, Strategies reqStrategies) {
        Optional<Strategies> objective = strategiesService.getStrategiesById(strategyCode);
        strategiesService.updateStrategies(strategyCode, reqStrategies);
        Strategies obje = objective.get();
        strategiesResponseDto responseDto = new strategiesResponseDto();
        responseDto.setStrategyCode(obje.getStrategyCode());
        responseDto.setStrategy(obje.getStrategy());
        responseDto.setObjectiveCode(obje.getObjectives().getObjectiveCode());
        responseDto.setStatus(obje.getStatus());
        responseDto.setCreatedDate(obje.getCreatedDate());
        responseDto.setCreatedBy(obje.getCreatedBy());
        responseDto.setModifiedDate(obje.getModifiedDate());
        responseDto.setModifiedBy(obje.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}

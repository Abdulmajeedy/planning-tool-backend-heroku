package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.strategiesRequestDto;
import com.hmy.planning.systemconfiguration.dto.strategiesResponseDto;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.repository.ObjectiveRepository;
import com.hmy.planning.systemconfiguration.service.StrategiesService;
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

    private StrategiesService strategiesService;
    private ObjectiveRepository objectiveRepo;
    private ModelMapper modelMapper;

    @Autowired
    public strategiesController(StrategiesService strategiesService, ModelMapper modelMapper,
            ObjectiveRepository objectiveRepo) {
        this.objectiveRepo = objectiveRepo;
        this.strategiesService = strategiesService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<strategiesResponseDto>> getStartegy(int page, int size) {
        return ResponseEntity.ok(strategiesService.findAllStrategies(page, size));

    }

    @Override
    public ResponseEntity<strategiesResponseDto> registerNewStartegy(strategiesRequestDto reqStrategies) {
        return strategiesService.addNewStrategies(reqStrategies);
    }

    @Override
    public ResponseEntity<strategiesResponseDto> getStartegyById(String strategyCode) {
        return ResponseEntity.ok(strategiesService.getStrategiesById(strategyCode));
    }

    @Override
    public void deleteById(String strategyCode) {
        strategiesService.deleteStrategies(strategyCode);

    }

    @Override
    public ResponseEntity updateStartegy(String strategyCode, Strategies reqStrategies) {
        return ResponseEntity.ok(strategiesService.updateStrategies(strategyCode, reqStrategies));
    }

    @Override
    public ResponseEntity updateStatus(String strategyCode) {
        return ResponseEntity.ok().body(strategiesService.updateStatus(strategyCode));
    }
}

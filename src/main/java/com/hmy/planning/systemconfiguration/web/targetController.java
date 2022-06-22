package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.targetRequestDto;
import com.hmy.planning.systemconfiguration.dto.targetResponseDto;
import com.hmy.planning.systemconfiguration.models.Target;
import com.hmy.planning.systemconfiguration.service.targetService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.targetApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class targetController implements targetApi {

    private targetService terService;
    private ModelMapper modelMapper;

    @Autowired
    public targetController(targetService terService, ModelMapper modelMapper) {

        this.terService = terService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<targetResponseDto>> getTarget(int page, int size) {
        return ResponseEntity.ok(terService.findAllTarget(page, size));
    }

    @Override
    public ResponseEntity<targetResponseDto> registerNewTarget(targetRequestDto reqTarget) {
        return terService.addNewTarget(reqTarget);
    }

    @Override
    public ResponseEntity<targetResponseDto> getTargetById(String targetCode) {
        return ResponseEntity.ok(terService.getTargetById(targetCode));
    }

    @Override
    public void deleteById(String targetCode) {
        terService.deleteTarget(targetCode);

    }

    @Override
    public ResponseEntity updateTarget(String targetCode, Target reqTarget) {
        return ResponseEntity.ok(terService.updateTarget(targetCode, reqTarget));
    }

    @Override
    public ResponseEntity updateStatus(String targetCode) {
        return ResponseEntity.ok().body(terService.updateStatus(targetCode));
    }
}

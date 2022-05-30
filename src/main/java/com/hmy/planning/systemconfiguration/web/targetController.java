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
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Target> terg = terService.findAllTarget(pageRequest);
        List<targetResponseDto> terDto = new ArrayList<>();
        for (Target object : terg) {
            targetResponseDto responseDto = new targetResponseDto();
            responseDto.setTargetCode(object.getTargetCode());
            responseDto.setTargetName(object.getTargetName());
            responseDto.setStatus(object.getStatus());
            responseDto.setCreatedDate(object.getCreatedDate());
            responseDto.setCreatedBy(object.getCreatedBy());
            responseDto.setModifiedDate(object.getModifiedDate());
            responseDto.setModifiedBy(object.getModifiedBy());
            terDto.add(responseDto);
        }
        return ResponseEntity.ok(terDto);
    }

    @Override
    public ResponseEntity<targetResponseDto> registerNewTarget(targetRequestDto reqTarget) {
        return terService.addNewTarget(reqTarget);
    }

    @Override
    public ResponseEntity<targetResponseDto> getTargetById(String targetCode) {
        Optional<Target> objective = terService.getTargetById(targetCode);
        if (!objective.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            Target obje = objective.get();
            targetResponseDto responseDto = new targetResponseDto();
            responseDto.setTargetCode(obje.getTargetCode());
            responseDto.setTargetName(obje.getTargetName());
            // responseDto.setStrategyCode(strategyCode);
            responseDto.setStatus(obje.getStatus());
            responseDto.setCreatedDate(obje.getCreatedDate());
            responseDto.setCreatedBy(obje.getCreatedBy());
            responseDto.setModifiedDate(obje.getModifiedDate());
            responseDto.setModifiedBy(obje.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String targetCode) {
        terService.deleteTarget(targetCode);

    }

    @Override
    public ResponseEntity updateTarget(String targetCode, Target reqTarget) {
        Optional<Target> objective = terService.getTargetById(targetCode);
        terService.updateTarget(targetCode, reqTarget);
        Target obje = objective.get();
        targetResponseDto responseDto = new targetResponseDto();
        responseDto.setTargetCode(obje.getTargetCode());
        responseDto.setTargetName(obje.getTargetName());
        // responseDto.setStrategyCode(strategyCode);
        responseDto.setStatus(obje.getStatus());
        responseDto.setCreatedDate(obje.getCreatedDate());
        responseDto.setCreatedBy(obje.getCreatedBy());
        responseDto.setModifiedDate(obje.getModifiedDate());
        responseDto.setModifiedBy(obje.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}

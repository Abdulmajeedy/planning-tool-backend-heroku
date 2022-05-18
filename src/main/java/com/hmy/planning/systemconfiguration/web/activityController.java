package com.hmy.planning.systemconfiguration.web;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.activityRequestDto;
import com.hmy.planning.systemconfiguration.dto.activityResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;
import com.hmy.planning.systemconfiguration.service.activityService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.activityApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class activityController implements activityApi {
    private activityService activityServices;
    private ActivityRepository activityRepo;
    private ModelMapper modelMapper;

    @Autowired
    public activityController(activityService activityServices, ModelMapper modelMapper) {

        this.activityServices = activityServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<activityResponseDto>> getActivities(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Activity> activity = activityServices.findAllActivities(pageRequest);
        List<activityResponseDto> actDto = new ArrayList<>();
        for (Activity act : activity) {
            activityResponseDto responseDto = new activityResponseDto();
            responseDto.setActivityCode(act.getActivityCode());
            responseDto.setActivityName(act.getActivityName());
            responseDto.setStrategyCode(act.getStrategies().getStrategyCode());
            responseDto.setStatus(act.getStatus());
            responseDto.setCreatedDate(act.getCreatedDate());
            responseDto.setCreatedBy(act.getCreatedBy());
            responseDto.setModifiedDate(act.getModifiedDate());
            responseDto.setModifiedBy(act.getModifiedBy());
            actDto.add(responseDto);
        }
        return ResponseEntity.ok(actDto);
    }

    @Override
    public ResponseEntity<activityResponseDto> registerNewActivity(activityRequestDto reqActivity) {
        return activityServices.addNewActivity(reqActivity);
    }

    @Override
    public ResponseEntity<activityResponseDto> getActivityById(String activityCode) {
        Optional<Activity> orgStr = activityServices.getActivityById(activityCode);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid Activity Code", null), HttpStatus.NOT_FOUND);
        } else {
            Activity bg = orgStr.get();
            activityResponseDto responseDto = new activityResponseDto();

            responseDto.setActivityCode(bg.getActivityCode());
            responseDto.setActivityName(bg.getActivityName());
            responseDto.setStrategyCode(bg.getStrategies().getStrategyCode());
            responseDto.setStatus(bg.getStatus());
            responseDto.setCreatedDate(bg.getCreatedDate());
            responseDto.setCreatedBy(bg.getCreatedBy());
            responseDto.setModifiedDate(bg.getModifiedDate());
            responseDto.setModifiedBy(bg.getModifiedBy());

            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public ResponseEntity updateActivity(String activityCode, Activity reqActivity) {
        Optional<Activity> budPer = activityServices.getActivityById(activityCode);
        activityServices.updateActivity(activityCode, reqActivity);
        Activity acty = budPer.get();
        activityResponseDto responseDto = new activityResponseDto();
        responseDto.setActivityName(acty.getActivityName());
        responseDto.setStrategyCode(acty.getStrategies().getStrategyCode());
        responseDto.setStatus(acty.getStatus());
        responseDto.setStatus(acty.getStatus());
        responseDto.setCreatedDate(acty.getCreatedDate());
        responseDto.setCreatedBy(acty.getCreatedBy());
        responseDto.setModifiedDate(acty.getModifiedDate());
        responseDto.setModifiedBy(acty.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public void deleteById(String activityCode) {
        activityServices.deleteActivity(activityCode);

    }
}

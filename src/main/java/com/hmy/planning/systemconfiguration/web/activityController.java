package com.hmy.planning.systemconfiguration.web;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.activityRequestDto;
import com.hmy.planning.systemconfiguration.dto.activityResponseDto;
import com.hmy.planning.systemconfiguration.dto.activityzResponseDto;
import com.hmy.planning.systemconfiguration.dto.graphByActivityResponseDto;
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
            responseDto.setTargetCode(act.getTargets().getTargetCode());
            responseDto.setOfficeID(act.getOrgStructures().getOfficeID());
            responseDto.setApprovalStatus(act.getApprovalStatus());
            responseDto.setStatus(act.getStatus());
            responseDto.setEditStatus(act.getEditStatus());
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
    public void deleteAll() {
        activityServices.deleteAllActivity();
    }

    // @Override
    // public ResponseEntity<List<activityzResponseDto>>
    // getActivitiesByOffice(String OfficeID) {

    // return ResponseEntity.ok(activityServices.findActivitiesByOffice(OfficeID));
    // }

    @Override
    public ResponseEntity<activityResponseDto> getActivityById(String activityCode) {
        Optional<Activity> orgStr = activityServices.getActivityById(activityCode);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid Activity Code", null), HttpStatus.NOT_FOUND);
        }
        Activity bg = orgStr.get();
        activityResponseDto responseDto = new activityResponseDto();

        responseDto.setActivityCode(bg.getActivityCode());
        responseDto.setActivityName(bg.getActivityName());
        responseDto.setTargetCode(bg.getTargets().getTargetCode());
        responseDto.setOfficeID(bg.getOrgStructures().getOfficeID());
        responseDto.setApprovalStatus(bg.getApprovalStatus());
        responseDto.setStatus(bg.getStatus());
        responseDto.setEditStatus(bg.getEditStatus());
        responseDto.setCreatedDate(bg.getCreatedDate());
        responseDto.setCreatedBy(bg.getCreatedBy());
        responseDto.setModifiedDate(bg.getModifiedDate());
        responseDto.setModifiedBy(bg.getModifiedBy());

        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity updateActivity(String activityCode, Activity reqActivity) {
        Optional<Activity> budPer = activityServices.getActivityById(activityCode);
        activityServices.updateActivity(activityCode, reqActivity);
        Activity acty = budPer.get();
        activityResponseDto responseDto = new activityResponseDto();
        responseDto.setActivityName(acty.getActivityName());
        responseDto.setTargetCode(acty.getTargets().getTargetCode());
        responseDto.setStatus(acty.getStatus());
        responseDto.setOfficeID(acty.getOrgStructures().getOfficeID());
        responseDto.setStatus(acty.getStatus());
        responseDto.setEditStatus(acty.getEditStatus());
        responseDto.setApprovalStatus(acty.getApprovalStatus());
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

    @Override
    public ResponseEntity updateStatus(String activityCode) {
        return ResponseEntity.ok().body(activityServices.updateStatus(activityCode));
    }

    @Override
    public ResponseEntity Count() {
        return ResponseEntity.ok().body(activityServices.CountActivities());
    }

    @Override
    public ResponseEntity getActivities() {
        // return ResponseEntity.ok().body(activityServices.GetActivities());
        // PageRequest pageRequest = PageRequest.of(page, size);
        List<Activity> activity = activityServices.GetActivities();
        List<activityzResponseDto> actDto = new ArrayList<>();
        for (Activity act : activity) {
            activityzResponseDto responseDto = new activityzResponseDto();
            responseDto.setActivityCode(act.getActivityCode());
            responseDto.setActivityName(act.getActivityName());
            responseDto.setEditStatus(act.getEditStatus());
            responseDto.setTargetCode(act.getTargets().getTargetCode());
            responseDto.setOfficeID(act.getOrgStructures().getOfficeID());
            responseDto
                    .setQuaterPeriodCode(act.getActivityQuaterPeriod().iterator().next().getActivityQuaterPeriodCode());
            responseDto.setStatus(act.getStatus());
            responseDto.setBudgetYearCode(
                    act.getActivityQuaterPeriod().iterator().next().getBudgetingPeriod().getBudgetYearCode());
            responseDto.setApprovedStatus(act.getApprovalStatus());
            responseDto.setCreatedDate(act.getCreatedDate());
            responseDto.setCreatedBy(act.getCreatedBy());
            responseDto.setModifiedDate(act.getModifiedDate());
            responseDto.setModifiedBy(act.getModifiedBy());
            actDto.add(responseDto);
        }
        return ResponseEntity.ok(actDto);
    }

    @Override
    public ResponseEntity GraphActivityByOffices() {

        return ResponseEntity.ok().body(activityServices.GraphActivityByOffice());

    }
    // public ResponseEntity GraphActivityByOffices() {
    // List<Activity> activity = activityServices.GraphActivityByOffice();
    // List<graphByActivityResponseDto> actDto = new ArrayList<>();
    // for (Activity act : activity) {
    // graphByActivityResponseDto responseDto = new graphByActivityResponseDto();

    @Override
    public ResponseEntity<List<activityzResponseDto>> getActivitiesByOffice(String officeID) {
        return ResponseEntity.ok(activityServices.findActivitiesByOffice(officeID));

    }

    // responseDto.setOfficeID(act.getOrgStructures().getOfficeID());

    // actDto.add(responseDto);
    // }
    // return ResponseEntity.ok(actDto);
    // }

}

package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.activityRequestDto;
import com.hmy.planning.systemconfiguration.dto.activityResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class activityService {

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private strategiesService strategiesService;

    @Autowired
    public activityService(ActivityRepository activityRepo, strategiesService strategiesService) {
        this.activityRepo = activityRepo;
        this.strategiesService = strategiesService;
    }

    public List<Activity> findAllActivities(PageRequest pageRequest) {
        return activityRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<activityResponseDto> addNewActivity(activityRequestDto reqActivity) {
        Optional<Strategies> strategies = strategiesService.getStrategiesCode(reqActivity.getStrategyCode());

        if (!strategies.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Strategies strategyObj = strategies.get();
        Activity act = new Activity();

        act.setActivityName(reqActivity.getActivityName());
        act.setStrategies(strategyObj);
        act.setStatus(reqActivity.getStatus());
        activityRepo.save(act);

        activityResponseDto actDto = new activityResponseDto();
        actDto.setActivityCode(act.getActivityCode());
        actDto.setActivityName(act.getActivityName());
        actDto.setStrategyCode(act.getStrategies().getStrategyCode());
        actDto.setStatus(act.getStatus());
        actDto.setCreatedDate(act.getCreatedDate());
        actDto.setCreatedBy(act.getCreatedBy());
        actDto.setModifiedDate(act.getModifiedDate());
        actDto.setModifiedBy(act.getModifiedBy());
        return ResponseEntity.ok(actDto);
    }

    public void deleteActivity(String strategyCode) {

        activityRepo.deleteById(strategyCode);
    }

    public Optional<Activity> getActivityById(String activityCode) {
        return activityRepo.findById(activityCode);
    }

    public void updateStrategies(String activityCode, Activity reqActivity) {
        activityRepo.findById(activityCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Office  with ID " + activityCode + " does not exist"));

        reqActivity.setActivityCode(activityCode);
        Activity acti = new Activity();
        acti.setCreatedBy(reqActivity.getCreatedBy());
        acti.setCreatedDate(reqActivity.getCreatedDate());
        acti.setModifiedBy(reqActivity.getModifiedBy());
        activityRepo.save(reqActivity);
    }

    public Optional<Activity> getActivityCode(String activityCode) {
        return activityRepo.findById(activityCode);
    }

}

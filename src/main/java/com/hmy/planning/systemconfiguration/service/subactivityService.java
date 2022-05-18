package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.subactivityRequestDto;
import com.hmy.planning.systemconfiguration.dto.subactivityResponseDto;
import com.hmy.planning.systemconfiguration.models.SubActivity;
import com.hmy.planning.systemconfiguration.repository.SubActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class subactivityService {

    @Autowired
    private SubActivityRepository subactivityRepo;

    @Autowired
    private strategiesService strategiesService;

    @Autowired
    public subactivityService(SubActivityRepository subactivityRepo, strategiesService strategiesService) {
        this.subactivityRepo = subactivityRepo;
        this.strategiesService = strategiesService;
    }

    public List<SubActivity> findAllActivities(PageRequest pageRequest) {
        return subactivityRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<subactivityResponseDto> addNewActivity(subactivityRequestDto reqSubActivity) {
        Optional<Strategies> strategies = strategiesService.getStrategiesCode(reqSubActivity.getStrategyCode());

        if (!strategies.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Strategies strategyObj = strategies.get();
        SubActivity act = new SubActivity();

        act.setActivityName(reqActivity.getActivityName());
        act.setStrategies(strategyObj);
        act.setStatus(reqActivity.getStatus());
        subactivityRepo.save(act);

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

    public void deleteSubActivity(String subactivityCode) {

        subactivityRepo.deleteById(subactivityCode);
    }

    public Optional<SubActivity> getActivityById(String activityCode) {
        return subactivityRepo.findById(activityCode);
    }

    public void updateSubActivity(String subactivityCode, SubActivity reqSubActivity) {
        subactivityRepo.findById(subactivityCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Office  with ID " + subactivityCode + " does not exist"));

        reqSubActivity.setSubactivityCode(subactivityCode);
        SubActivity acti = new SubActivity();
        acti.setCreatedBy(reqActivity.getCreatedBy());
        acti.setCreatedDate(reqActivity.getCreatedDate());
        acti.setModifiedBy(reqActivity.getModifiedBy());
        subactivityRepo.save(reqActivity);
    }

    public Optional<Activity> getActivityCode(String activityCode) {
        return subactivityRepo.findById(activityCode);
    }

}

package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.subactivityRequestDto;
import com.hmy.planning.systemconfiguration.dto.subactivityResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.SubActivity;
import com.hmy.planning.systemconfiguration.repository.SubActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class subactivityService {

    @Autowired
    private SubActivityRepository subactivityRepo;

    @Autowired
    private strategiesService strategiesService;

    @Autowired
    private activityService activityService;

    @Autowired
    public subactivityService(SubActivityRepository subactivityRepo, strategiesService strategiesService,
            activityService activityService) {
        this.subactivityRepo = subactivityRepo;
        this.activityService = activityService;
        this.strategiesService = strategiesService;
    }

    public List<SubActivity> findAllActivities(PageRequest pageRequest) {
        return subactivityRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<subactivityResponseDto> addNewActivity(subactivityRequestDto reqSubActivity) {
        Optional<Activity> activitites = activityService.getActivityCode(reqSubActivity.getActivityCode());

        if (!activitites.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Activity activityObj = activitites.get();
        SubActivity act = new SubActivity();

        act.setSubactivityName(reqSubActivity.getSubactivityName());
        act.setActivity(activityObj);
        act.setGfsCode(reqSubActivity.getGfsCode());
        act.setEstimates(reqSubActivity.getEstimates());
        act.setNo_of_unit(reqSubActivity.getNo_of_unit());
        act.setUnit_cost_per_unit(reqSubActivity.getUnit_cost_per_unit());
        act.setStatus(reqSubActivity.getStatus());
        subactivityRepo.save(act);

        subactivityResponseDto actDto = new subactivityResponseDto();
        actDto.setSubactivityName(act.getSubactivityName());
        actDto.setActivity(act.getActivity().getActivityCode());
        actDto.setGfsCode(act.getGfsCode());
        actDto.setEstimates(act.getEstimates());
        actDto.setNo_of_unit(act.getNo_of_unit());
        actDto.setUnit_cost_per_unit(act.getUnit_cost_per_unit());
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
                        "Sub Activity  with Code " + subactivityCode + " does not exist"));

        reqSubActivity.setSubactivityCode(subactivityCode);
        SubActivity acti = new SubActivity();
        acti.setCreatedBy(reqSubActivity.getCreatedBy());
        acti.setCreatedDate(reqSubActivity.getCreatedDate());
        acti.setModifiedBy(reqSubActivity.getModifiedBy());
        subactivityRepo.save(reqSubActivity);
    }

    public Optional<SubActivity> getActivityCode(String subactivityCode) {
        return subactivityRepo.findById(subactivityCode);
    }

}

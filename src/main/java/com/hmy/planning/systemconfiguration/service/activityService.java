package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.activityRequestDto;
import com.hmy.planning.systemconfiguration.dto.activityResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.ActivityQuaterPeriod;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.repository.ActivityQuaterPeriodRepository;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Return;

@Service
public class activityService {

    @Autowired
    private ActivityRepository activityRepo;
    private ActivityQuaterPeriodRepository activityQuaterPeriodRepository;

    @Autowired
    private strategiesService strategiesService;
    private quaterPeriodService quaterPd;

    private ModelMapper modelMapper;

    @Autowired
    public activityService(ActivityRepository activityRepo, strategiesService strategiesService,
            ModelMapper modelMapper) {
        this.activityRepo = activityRepo;
        this.strategiesService = strategiesService;
        this.modelMapper = modelMapper;
    }

    public List<Activity> findAllActivities(PageRequest pageRequest) {
        return activityRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<activityResponseDto> addNewActivity(activityRequestDto reqActivity) {
        Optional<Strategies> strategies = strategiesService.getStrategiesCode(reqActivity.getStrategyCode());
        // Optional<QuaterPeriod> qPeriod =
        // quaterPd.getQuaterPeriodCode(reqActivity.getQuaterPeriodCode());

        if (!strategies.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // if (!qPeriod.isPresent()) {
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }

        Strategies strategyObj = strategies.get();
        // QuaterPeriod quaterPeriodObj = qPeriod.get();
        Activity act = new Activity();
        // Activity act = modelMapper.map(reqActivity, Activity.class);

        act.setActivityName(reqActivity.getActivityName());
        act.setStrategies(strategyObj);
        act.setStatus(reqActivity.getStatus());
        activityRepo.save(act);
        ActivityQuaterPeriod quaterperiods = new ActivityQuaterPeriod();
        Activity actvty = new Activity();
        actvty.setActivityCode(act.getActivityCode());
        quaterperiods.setActivityQuaterPeriodCode(reqActivity.getQuaterPeriodCode());
        QuaterPeriod quaterPeriodObject = new QuaterPeriod();
        quaterperiods.setQuaterPeriod(quaterPeriodObject);
        quaterperiods.setActivity(actvty);
        activityQuaterPeriodRepository.save(quaterperiods);

        // activityResponseDto actDto = new activityResponseDto();
        activityResponseDto actDto = modelMapper.map(act, activityResponseDto.class);
        // actDto.setActivityCode(act.getActivityCode());
        // actDto.setActivityName(act.getActivityName());
        // actDto.setStrategyCode(act.getStrategies().getStrategyCode());
        // actDto.setQuaterPeriodCode();
        // actDto.setStatus(act.getStatus());
        // actDto.setCreatedDate(act.getCreatedDate());
        // actDto.setCreatedBy(act.getCreatedBy());
        // actDto.setModifiedDate(act.getModifiedDate());
        // actDto.setModifiedBy(act.getModifiedBy());
        return ResponseEntity.ok(actDto);
    }

    public void deleteActivity(String activityCode) {

        activityRepo.deleteById(activityCode);
    }

    public Optional<Activity> getActivityById(String activityCode) {
        return activityRepo.findById(activityCode);
    }

    public void updateActivity(String activityCode, Activity reqActivity) {
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

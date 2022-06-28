package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.activityRequestDto;
import com.hmy.planning.systemconfiguration.dto.activityResponseDto;
import com.hmy.planning.systemconfiguration.dto.activityzResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.ActivityQuaterPeriod;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.models.Target;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.repository.ActivityQuaterPeriodRepository;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;
import com.hmy.planning.systemconfiguration.web.api.quaterPeriodApi;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import net.bytebuddy.asm.Advice.Return;

@Service
@Data
public class activityService {

    @Autowired
    private ActivityRepository activityRepo;
    private final ActivityQuaterPeriodRepository activityQuaterPeriodRepository;

    @Autowired
    private final targetService targService;
    private final QuaterPeriodService quaterPd;
    private final orgStructureService orgServices;
    private final budgetPeriodService budgetPeriodServices;

    private final ModelMapper modelMapper;

    // @Autowired
    // public activityService(ActivityRepository activityRepo, strategiesService
    // strategiesService,
    // quaterPeriodService quaterPd,
    // ModelMapper modelMapper) {
    // this.activityRepo = activityRepo;
    // this.strategiesService = strategiesService;
    // this.quaterPd = quaterPd;
    // this.modelMapper = modelMapper;
    // }

    public List<Activity> findAllActivities(PageRequest pageRequest) {
        return activityRepo.findAll(pageRequest).getContent();
    }

    public List<activityzResponseDto> findActivitiesByOffice(String officeID) {
        List<Activity> activities = activityRepo.findActivitiesByOffice(officeID);
        List<activityzResponseDto> actDto = new ArrayList<>();
        for (Activity Act : activities) {
            activityzResponseDto responseDto = modelMapper.map(Act, activityzResponseDto.class);
            responseDto.setApprovedStatus(Act.getApprovalStatus());
            responseDto.setEditStatus(Act.getEditStatus());
            responseDto.setOfficeID(Act.getOrgStructures().getOfficeID());
            responseDto.setBudgetYearCode(
                    Act.getActivityQuaterPeriod().iterator().next().getBudgetingPeriod().getBudgetYearCode());
            responseDto.setTargetCode(Act.getTargets().getTargetCode());
            responseDto.setQuaterPeriodCode(
                    Act.getActivityQuaterPeriod().iterator().next().getBudgetingPeriod().getBudgetYearCode());
            actDto.add(responseDto);
        }
        return actDto;
    }

    public ResponseEntity<activityResponseDto> addNewActivity(activityRequestDto reqActivity) {
        Optional<Target> target = targService.getTargetCode(reqActivity.getTargetCode());
        Optional<QuaterPeriod> qPeriod = quaterPd.getQuaterPeriodCode(reqActivity.getQuaterPeriodCode());
        Optional<orgStructure> orgStructure = orgServices.getOrgStructureID(reqActivity.getOfficeID());
        Optional<budgetingPeriod> budp = budgetPeriodServices.getBudgetYearCode(reqActivity.getBudgetYearCode());

        if (!target.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!budp.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!qPeriod.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!orgStructure.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Target targyObj = new Target();
        targyObj.setTargetCode(reqActivity.getTargetCode());

        orgStructure orgObj = new orgStructure();
        orgObj.setOfficeID(reqActivity.getOfficeID());

        budgetingPeriod budObj = new budgetingPeriod();
        budObj.setBudgetYearCode(reqActivity.getBudgetYearCode());

        Activity act = new Activity();

        act.setActivityName(reqActivity.getActivityName());
        act.setTargets(targyObj);
        act.setOrgStructures(orgObj);
        act.setStatus(reqActivity.getStatus());
        act.setApprovalStatus(reqActivity.getApprovalStatus());
        activityRepo.save(act);

        ActivityQuaterPeriod quaterperiods = new ActivityQuaterPeriod();
        Activity actvty = new Activity();
        actvty.setActivityCode(act.getActivityCode());

        quaterperiods.setActivityQuaterPeriodCode(reqActivity.getQuaterPeriodCode());
        quaterperiods.setBudgetingPeriod(budObj);
        QuaterPeriod quaterPeriodObject = new QuaterPeriod();
        quaterPeriodObject.setQuaterPeriodCode(reqActivity.getQuaterPeriodCode());
        quaterperiods.setQuaterPeriod(quaterPeriodObject);
        quaterperiods.setStatus(reqActivity.getStatus());
        quaterperiods.setActivity(actvty);
        activityQuaterPeriodRepository.save(quaterperiods);

        activityResponseDto actDto = new activityResponseDto();
        actDto.setActivityCode(act.getActivityCode());
        actDto.setActivityName(act.getActivityName());
        actDto.setOfficeID(act.getOrgStructures().getOfficeID());
        actDto.setStatus(act.getStatus());
        actDto.setBudgetYearCode(quaterperiods.getBudgetingPeriod().getBudgetYearCode());
        actDto.setTargetCode(act.getTargets().getTargetCode());
        actDto.setApprovalStatus(act.getApprovalStatus());
        actDto.setEditStatus(act.getEditStatus());

        // actDto.setQuaterPeriodCode();
        actDto.setStatus(act.getStatus());
        actDto.setCreatedDate(act.getCreatedDate());
        actDto.setCreatedBy(act.getCreatedBy());
        actDto.setModifiedDate(act.getModifiedDate());
        actDto.setModifiedBy(act.getModifiedBy());
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

    public Map<String, Boolean> updateStatus(String activityCode) {
        Optional<Activity> project = activityRepo.findById(activityCode);
        if (!project.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (project.get().getStatus() == 1)
            project.get().setStatus(0);
        else
            project.get().setStatus(1);
        activityRepo.save(project.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    public Long CountActivities() {
        return activityRepo.CountActivities();
    }

    public List<Activity> GetActivities() {
        return activityRepo.GetActivities();
    }

    public List<Map<String, Object>> GraphActivityByOffice() {
        return activityRepo.GraphActivityByOffice();

    }

}

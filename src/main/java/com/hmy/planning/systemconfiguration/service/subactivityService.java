package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.subactivityRequestDto;
import com.hmy.planning.systemconfiguration.dto.subactivityResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.SubActivity;
import com.hmy.planning.systemconfiguration.repository.SubActivityRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class subactivityService {

    @Autowired
    private SubActivityRepository subactivityRepo;

    @Autowired
    private StrategiesService strategiesService;

    @Autowired
    private activityService activityService;
    private final ModelMapper modelmapper;

    public List<SubActivity> findAllSubActivities(PageRequest pageRequest) {
        return subactivityRepo.findAll(pageRequest).getContent();
    }

    public List<subactivityResponseDto> getSubActivityByActivityCode(String activityCode) {
        List<SubActivity> subActivities = subactivityRepo.findByActivityCode(activityCode);
        // List<SubActivity> subActivities = subactivityRepo.findAllById(activityCode);
        List<subactivityResponseDto> roleDto = new ArrayList<>();
        for (SubActivity sub : subActivities) {
            subactivityResponseDto responseDto = modelmapper.map(sub, subactivityResponseDto.class);
            roleDto.add(responseDto);
        }
        return roleDto;

    }

    public ResponseEntity<subactivityResponseDto> addNewSubActivity(subactivityRequestDto reqSubActivity) {
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
        actDto.setSubactivityCode(act.getSubactivityCode());
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

    public Optional<SubActivity> getSubActivityById(String activityCode) {
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

    public Optional<SubActivity> getSubActivityCode(String subactivityCode) {
        return subactivityRepo.findById(subactivityCode);
    }

    public Map<String, Boolean> updateStatus(String officeID) {
        Optional<SubActivity> bp = subactivityRepo.findById(officeID);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        subactivityRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

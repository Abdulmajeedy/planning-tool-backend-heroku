package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.subactivityRequestDto;
import com.hmy.planning.systemconfiguration.dto.subactivityResponseDto;
import com.hmy.planning.systemconfiguration.models.SubActivity;
import com.hmy.planning.systemconfiguration.repository.SubActivityRepository;
import com.hmy.planning.systemconfiguration.service.subactivityService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.subActivityApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class subActivityController implements subActivityApi {

    private subactivityService subActivityServices;
    private SubActivityRepository subActRepo;
    private ModelMapper modelMapper;

    @Autowired
    public subActivityController(subactivityService subActivityServices, ModelMapper modelMapper) {

        this.subActivityServices = subActivityServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<subactivityResponseDto>> getSubActivity(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SubActivity> sub = subActivityServices.findAllSubActivities(pageRequest);
        List<subactivityResponseDto> roleDto = new ArrayList<>();
        for (SubActivity bg : sub) {
            subactivityResponseDto responseDto = new subactivityResponseDto();
            responseDto.setSubactivityCode(bg.getSubactivityCode());
            responseDto.setSubactivityName(bg.getSubactivityName());
            // responseDto.setActivity(bg.getActivity().getActivityCode());
            responseDto.setGfsCode(bg.getGfsCode());
            responseDto.setEstimates(bg.getEstimates());
            responseDto.setNo_of_unit(bg.getNo_of_unit());
            responseDto.setUnit_cost_per_unit(bg.getUnit_cost_per_unit());
            responseDto.setStatus(bg.getStatus());
            responseDto.setCreatedDate(bg.getCreatedDate());
            responseDto.setCreatedBy(bg.getCreatedBy());
            responseDto.setModifiedDate(bg.getModifiedDate());
            responseDto.setModifiedBy(bg.getModifiedBy());

            roleDto.add(responseDto);
        }
        return ResponseEntity.ok(roleDto);
    }

    @Override
    public ResponseEntity<subactivityResponseDto> registerNewSubActivity(subactivityRequestDto reqSubActivity) {
        return subActivityServices.addNewSubActivity(reqSubActivity);
    }

    @Override
    public ResponseEntity<subactivityResponseDto> getSubActivityById(String subactivityCode) {
        Optional<SubActivity> orgStr = subActivityServices.getSubActivityById(subactivityCode);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            SubActivity bg = orgStr.get();
            subactivityResponseDto responseDto = new subactivityResponseDto();

            responseDto.setSubactivityCode(bg.getSubactivityCode());
            responseDto.setSubactivityName(bg.getSubactivityName());
            responseDto.setActivity(bg.getActivity().getActivityCode());
            responseDto.setGfsCode(bg.getGfsCode());
            responseDto.setEstimates(bg.getEstimates());
            responseDto.setNo_of_unit(bg.getNo_of_unit());
            responseDto.setUnit_cost_per_unit(bg.getUnit_cost_per_unit());
            responseDto.setStatus(bg.getStatus());
            responseDto.setCreatedDate(bg.getCreatedDate());
            responseDto.setCreatedBy(bg.getCreatedBy());
            responseDto.setModifiedDate(bg.getModifiedDate());
            responseDto.setModifiedBy(bg.getModifiedBy());

            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String subactivityCode) {
        subActivityServices.deleteSubActivity(subactivityCode);

    }

    @Override
    public ResponseEntity updateSubActivity(String subactivityCode, SubActivity reqSubActivity) {
        Optional<SubActivity> budPer = subActivityServices.getSubActivityById(subactivityCode);
        subActivityServices.updateSubActivity(subactivityCode, reqSubActivity);
        SubActivity budgetPer = budPer.get();
        subactivityResponseDto responseDto = new subactivityResponseDto();

        responseDto.setSubactivityCode(budgetPer.getSubactivityCode());
        responseDto.setSubactivityName(budgetPer.getSubactivityName());
        // responseDto.setActivity(budgetPer.getActivity().getActivityCode());
        responseDto.setGfsCode(budgetPer.getGfsCode());
        responseDto.setEstimates(budgetPer.getEstimates());
        responseDto.setNo_of_unit(budgetPer.getNo_of_unit());
        responseDto.setUnit_cost_per_unit(budgetPer.getUnit_cost_per_unit());
        responseDto.setStatus(budgetPer.getStatus());
        responseDto.setCreatedDate(budgetPer.getCreatedDate());
        responseDto.setCreatedBy(budgetPer.getCreatedBy());
        responseDto.setModifiedDate(budgetPer.getModifiedDate());
        responseDto.setModifiedBy(budgetPer.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity updateStatus(String subactivityCode) {
        return ResponseEntity.ok().body(subActivityServices.updateStatus(subactivityCode));
    }

    @Override
    public ResponseEntity<List<subactivityResponseDto>> getSubActivityByActivityCode(String activityCode) {
        return ResponseEntity.ok(subActivityServices.getSubActivityByActivityCode(activityCode));
    }

}

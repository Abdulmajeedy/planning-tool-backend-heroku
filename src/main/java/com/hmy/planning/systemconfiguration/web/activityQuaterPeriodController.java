package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.activityQuaterPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.ActivityQuaterPeriod;
import com.hmy.planning.systemconfiguration.repository.ActivityQuaterPeriodRepository;
import com.hmy.planning.systemconfiguration.service.ActivityQuterPeriodService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.ActivityQuaterPeriodApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class activityQuaterPeriodController implements ActivityQuaterPeriodApi {

    private ActivityQuterPeriodService actQuaterServices;
    private ActivityQuaterPeriodRepository QuaterPeriodRepo;
    private ModelMapper modelMapper;

    @Autowired
    public activityQuaterPeriodController(ActivityQuterPeriodService actQuaterServices, ModelMapper modelMapper) {

        this.actQuaterServices = actQuaterServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<activityQuaterPeriodResponseDto>> getActivityQuaterPeriod(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<ActivityQuaterPeriod> role = actQuaterServices.findAllActivityQuaterPeriod(pageRequest);
        List<activityQuaterPeriodResponseDto> roleDto = new ArrayList<>();
        for (ActivityQuaterPeriod rol : role) {
            activityQuaterPeriodResponseDto responseDto = new activityQuaterPeriodResponseDto();

            responseDto.setActivityQuaterPeriodCode(rol.getActivityQuaterPeriodCode());
            responseDto.setActivityCode(rol.getActivity().getActivityCode());
            responseDto.setQuaterPeriodCode(rol.getQuaterPeriod().getQuaterPeriodCode());
            responseDto.setCreatedDate(rol.getCreatedDate());
            responseDto.setCreatedBy(rol.getCreatedBy());
            responseDto.setModifiedDate(rol.getModifiedDate());
            responseDto.setModifiedBy(rol.getModifiedBy());
            roleDto.add(responseDto);
        }
        return ResponseEntity.ok(roleDto);
    }

    @Override
    public ResponseEntity<activityQuaterPeriodResponseDto> getgetActivityQuaterPeriodById(
            String activityQuaterPeriodCode) {
        Optional<ActivityQuaterPeriod> orgStr = actQuaterServices
                .getActivityQuaterPeriodById(activityQuaterPeriodCode);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        }
        ActivityQuaterPeriod bg = orgStr.get();
        activityQuaterPeriodResponseDto responseDto = new activityQuaterPeriodResponseDto();

        responseDto.setActivityQuaterPeriodCode(bg.getActivityQuaterPeriodCode());
        responseDto.setActivityCode(bg.getActivity().getActivityCode());
        responseDto.setQuaterPeriodCode(bg.getQuaterPeriod().getQuaterPeriodCode());
        responseDto.setStatus(bg.getStatus());
        responseDto.setCreatedDate(bg.getCreatedDate());
        responseDto.setCreatedBy(bg.getCreatedBy());
        responseDto.setModifiedDate(bg.getModifiedDate());
        responseDto.setModifiedBy(bg.getModifiedBy());

        return ResponseEntity.ok(responseDto);
    }

    @Override
    public void deleteById(String activityQuaterPeriodCode) {
        actQuaterServices.deleteActivityQuaterPeriod(activityQuaterPeriodCode);

    }

    @Override
    public ResponseEntity updategetActivityQuaterPeriod(String activityCode,
            ActivityQuaterPeriod activityQuaterPeriod) {
        // TODO Auto-generated method stub
        return null;
    }

}

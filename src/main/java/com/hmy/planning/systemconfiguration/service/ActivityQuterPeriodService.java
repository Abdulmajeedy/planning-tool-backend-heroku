package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.models.ActivityQuaterPeriod;
import com.hmy.planning.systemconfiguration.repository.ActivityQuaterPeriodRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class ActivityQuterPeriodService {
    @Autowired
    private final ActivityQuaterPeriodRepository activityQuaterPeriodRepository;

    @Autowired
    private final StrategiesService strategiesService;
    private final QuaterPeriodService quaterPd;

    private final ModelMapper modelMapper;

    public List<ActivityQuaterPeriod> findAllActivityQuaterPeriod(PageRequest pageRequest) {
        return activityQuaterPeriodRepository.findAll(pageRequest).getContent();
    }

    public void deleteActivityQuaterPeriod(String activityCode) {

        activityQuaterPeriodRepository.deleteById(activityCode);
    }

    public Optional<ActivityQuaterPeriod> getActivityQuaterPeriodById(String activityCode) {
        return activityQuaterPeriodRepository.findById(activityCode);
    }

    public void updateActivityQuaterPeriod(String ActivityQuaterPeriodCode,
            ActivityQuaterPeriod reqActivityQuaterPeriod) {
        activityQuaterPeriodRepository.findById(
                ActivityQuaterPeriodCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Office  with ID " + ActivityQuaterPeriodCode + " does not exist"));

        reqActivityQuaterPeriod.setActivityQuaterPeriodCode(ActivityQuaterPeriodCode);
        ActivityQuaterPeriod acti = new ActivityQuaterPeriod();
        acti.setCreatedBy(reqActivityQuaterPeriod.getCreatedBy());
        acti.setCreatedDate(reqActivityQuaterPeriod.getCreatedDate());
        acti.setModifiedBy(reqActivityQuaterPeriod.getModifiedBy());
        activityQuaterPeriodRepository.save(reqActivityQuaterPeriod);
    }

    public Optional<ActivityQuaterPeriod> getActivityCode(String activityCode) {
        return activityQuaterPeriodRepository.findById(activityCode);
    }

}

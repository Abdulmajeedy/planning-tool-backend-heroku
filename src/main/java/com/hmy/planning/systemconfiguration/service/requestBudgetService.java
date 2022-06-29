package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hmy.planning.systemconfiguration.dto.requestBudgetRequestDto;
import com.hmy.planning.systemconfiguration.dto.requestBudgetResponseDto;
import com.hmy.planning.systemconfiguration.models.Activity;
import com.hmy.planning.systemconfiguration.models.RequestBudget;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;
import com.hmy.planning.systemconfiguration.repository.BudgetPeriodRepository;
import com.hmy.planning.systemconfiguration.repository.RequestBudgetRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class requestBudgetService {

    @Autowired
    private RequestBudgetRepository requestBudgetRepo;
    private ActivityRepository activityRepo;
    private final BudgetPeriodRepository budgetPeriodRepo;
    private final ModelMapper modelmapper;
    private budgetPeriodService budgetPeriodService;

    public List<requestBudgetResponseDto> findAllRequestBudget(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<RequestBudget> obj = requestBudgetRepo.findAll(pageRequest).getContent();
        List<requestBudgetResponseDto> objDto = new ArrayList<>();
        for (RequestBudget objective : obj) {
            requestBudgetResponseDto responseDto = modelmapper.map(objective, requestBudgetResponseDto.class);
            objDto.add(responseDto);
        }
        return objDto;
    }

    public ResponseEntity<requestBudgetResponseDto> addNewRequestBudget(requestBudgetRequestDto reqRequestBudget) {
        log.info(reqRequestBudget.toString());
        Optional<Activity> activity = activityRepo.findById(reqRequestBudget.getActivityCode());

        Activity activityObj = new Activity();
        activityObj.setActivityCode(activity.get().getActivityCode());

        RequestBudget objtvs = modelmapper.map(reqRequestBudget, RequestBudget.class);
        objtvs.setActivity(activityObj);
        requestBudgetRepo.save(objtvs);

        requestBudgetResponseDto obj = modelmapper.map(objtvs, requestBudgetResponseDto.class);
        return ResponseEntity.ok(obj);
    }

    public void deleteRequestBudget(String objectiveCode) {

        requestBudgetRepo.deleteById(objectiveCode);
    }

    public void deleteAllRequestBudget() {

        requestBudgetRepo.deleteAll();
    }

    public requestBudgetResponseDto getRequestBudgetById(String requestBudgetCode) {
        Optional<RequestBudget> office = requestBudgetRepo.findById(requestBudgetCode);
        if (!office.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + requestBudgetCode + "is not Found");
        } else {
            RequestBudget obj = office.get();
            requestBudgetResponseDto responseDto = modelmapper.map(obj, requestBudgetResponseDto.class);
            responseDto.setActivityCode(obj.getActivity().getActivityCode());
            return responseDto;
        }
    }

    public Optional<RequestBudget> getRequestBudgetCode(String ObjectiveCode) {
        return requestBudgetRepo.findById(ObjectiveCode);
    }

    public Map<String, Boolean> updateStatus(String ObjectiveCode) {
        Optional<RequestBudget> bp = requestBudgetRepo.findById(ObjectiveCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        requestBudgetRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

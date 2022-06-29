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
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.repository.ActivityRepository;
import com.hmy.planning.systemconfiguration.repository.RequestBudgetRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class requestBudgetService {

    @Autowired
    private RequestBudgetRepository reqBudgetRepo;
    private final budgetPeriodService budgetPeriodServices;
    private final activityService actService;
    private final orgStructureService orgService;
    private final ModelMapper modelmapper;

    public List<requestBudgetResponseDto> findAllrequestBudget(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<RequestBudget> impPeriod = reqBudgetRepo.findAll(pageRequest).getContent();
        List<requestBudgetResponseDto> roleDto = new ArrayList<>();
        for (RequestBudget imp : impPeriod) {
            requestBudgetResponseDto responseDto = modelmapper.map(imp, requestBudgetResponseDto.class);
            responseDto.setActivityCode(imp.getActivity().getActivityCode());
            responseDto.setOfficeID(imp.getOrgStructure().getOfficeID());
            roleDto.add(responseDto);
        }
        return roleDto;
    }

    public ResponseEntity<requestBudgetResponseDto> addNewrequestBudget(requestBudgetRequestDto reqReqBudget) {
        log.info(reqReqBudget.toString());
        Optional<Activity> qPeriod = actService.getActivityCode(reqReqBudget.getActivityCode());
        Optional<orgStructure> org = orgService.getOrgStructureID(reqReqBudget.getOfficeID());

        if (!qPeriod.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!org.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Activity actiObj = new Activity();
        actiObj.setActivityCode(reqReqBudget.getActivityCode());

        orgStructure orgObj = new orgStructure();
        orgObj.setOfficeID(reqReqBudget.getOfficeID());

        RequestBudget role = modelmapper.map(reqReqBudget, RequestBudget.class);
        role.setActivity(actiObj);
        role.setOrgStructure(orgObj);
        reqBudgetRepo.save(role);

        requestBudgetResponseDto rol = modelmapper.map(role, requestBudgetResponseDto.class);
        rol.setActivityCode(role.getActivity().getActivityCode());
        rol.setOfficeID(role.getOrgStructure().getOfficeID());
        return ResponseEntity.ok(rol);

    }

    public void deleterequestBudget(String impPeriodCode) {

        reqBudgetRepo.deleteById(impPeriodCode);
    }

    public requestBudgetResponseDto getrequestBudgetById(String impPeriodCode) {
        Optional<RequestBudget> roles = reqBudgetRepo.findById(impPeriodCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + impPeriodCode + "is not Found");
        } else {
            RequestBudget rol = roles.get();
            requestBudgetResponseDto responseDto = modelmapper.map(rol, requestBudgetResponseDto.class);
            return responseDto;
        }
    }

    public ResponseEntity<requestBudgetResponseDto> updaterequestBudget(String requestBudgetCode,
            requestBudgetRequestDto reqImp) {

        Optional<RequestBudget> roles = reqBudgetRepo.findById(requestBudgetCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + requestBudgetCode + "is not Found");
        }
        RequestBudget rol = modelmapper.map(reqImp, RequestBudget.class);
        rol.setRequestBudgetCode(requestBudgetCode);
        reqBudgetRepo.save(rol);

        requestBudgetResponseDto roltDto = modelmapper.map(rol, requestBudgetResponseDto.class);
        return ResponseEntity.ok(roltDto);

    }

    public Map<String, Boolean> updateStatus(String impPeriodCode) {
        Optional<RequestBudget> bp = reqBudgetRepo.findById(impPeriodCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        reqBudgetRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    public Optional<RequestBudget> getrequestBudgetCode(String impPeriodCode) {
        return reqBudgetRepo.findById(impPeriodCode);
    }

}

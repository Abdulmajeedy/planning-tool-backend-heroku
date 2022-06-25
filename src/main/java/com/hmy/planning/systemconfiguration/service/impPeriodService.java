package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hmy.planning.systemconfiguration.dto.impPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.impPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;
import com.hmy.planning.systemconfiguration.models.budgetingPeriod;
import com.hmy.planning.systemconfiguration.models.impPeriod;
import com.hmy.planning.systemconfiguration.repository.implPeriodRepository;

import lombok.Data;

@Service
@Data
public class impPeriodService {

    @Autowired
    private implPeriodRepository impPeriodRepo;
    private final budgetPeriodService budgetPeriodServices;
    private final QuaterPeriodService quaterPd;
    private final ModelMapper modelmapper;

    public List<impPeriodResponseDto> findAllImplementatedPeriod(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<impPeriod> impPeriod = impPeriodRepo.findAll(pageRequest).getContent();
        List<impPeriodResponseDto> roleDto = new ArrayList<>();
        for (impPeriod imp : impPeriod) {
            impPeriodResponseDto responseDto = modelmapper.map(imp, impPeriodResponseDto.class);
            roleDto.add(responseDto);
        }
        return roleDto;
    }

    public ResponseEntity<impPeriodResponseDto> addNewImpPeriod(impPeriodRequestDto reqImp) {
        Optional<QuaterPeriod> qPeriod = quaterPd.getQuaterPeriodCode(reqImp.getQuaterPeriodCode());
        Optional<budgetingPeriod> budp = budgetPeriodServices.getBudgetYearCode(reqImp.getBudgetYearCode());

        if (!budp.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!qPeriod.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        budgetingPeriod budObj = new budgetingPeriod();
        budObj.setBudgetYearCode(reqImp.getBudgetYearCode());

        QuaterPeriod qPeriodObj = new QuaterPeriod();
        qPeriodObj.setQuaterPeriodCode(reqImp.getQuaterPeriodCode());

        impPeriod role = modelmapper.map(reqImp, impPeriod.class);
        role.setBudgetingPeriod(budObj);
        role.setQuaterPeriod(qPeriodObj);
        impPeriodRepo.save(role);

        impPeriodResponseDto rol = modelmapper.map(role, impPeriodResponseDto.class);
        rol.setBudgetYearCode(role.getBudgetingPeriod().getBudgetYearCode());
        rol.setQuaterPeriodCode(role.getQuaterPeriod().getQuaterPeriodCode());
        return ResponseEntity.ok(rol);

    }

    public void deleteImpPeriod(String impPeriodCode) {

        impPeriodRepo.deleteById(impPeriodCode);
    }

    public impPeriodResponseDto getImpPeriodById(String impPeriodCode) {
        Optional<impPeriod> roles = impPeriodRepo.findById(impPeriodCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + impPeriodCode + "is not Found");
        } else {
            impPeriod rol = roles.get();
            impPeriodResponseDto responseDto = modelmapper.map(rol, impPeriodResponseDto.class);
            return responseDto;
        }
    }

    public ResponseEntity<impPeriodResponseDto> updateImpPeriod(String impPeriodCode, impPeriodRequestDto reqImp) {

        Optional<impPeriod> roles = impPeriodRepo.findById(impPeriodCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + impPeriodCode + "is not Found");
        }
        impPeriod rol = modelmapper.map(reqImp, impPeriod.class);
        rol.setImpPeriodCode(impPeriodCode);
        impPeriodRepo.save(rol);

        impPeriodResponseDto roltDto = modelmapper.map(rol, impPeriodResponseDto.class);
        return ResponseEntity.ok(roltDto);

    }

    public Map<String, Boolean> updateStatus(String impPeriodCode) {
        Optional<impPeriod> bp = impPeriodRepo.findById(impPeriodCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        impPeriodRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    public Optional<impPeriod> getImpPeriodCode(String impPeriodCode) {
        return impPeriodRepo.findById(impPeriodCode);
    }

}

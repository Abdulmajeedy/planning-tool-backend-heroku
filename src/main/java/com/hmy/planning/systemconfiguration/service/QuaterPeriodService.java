package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.quaterPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.quaterPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;
import com.hmy.planning.systemconfiguration.repository.QuaterPeriodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class QuaterPeriodService {

    @Autowired
    private QuaterPeriodRepository quaterRepo;

    @Autowired
    public QuaterPeriodService(QuaterPeriodRepository quaterRepo) {
        this.quaterRepo = quaterRepo;

    }

    public List<QuaterPeriod> findAllQuaterPeriod(PageRequest pageRequest) {
        return quaterRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<quaterPeriodResponseDto> addNewQuaterPeriod(quaterPeriodRequestDto reqQuaterPeriodCode) {
        QuaterPeriod quater = new QuaterPeriod();
        quater.setQuaterName(reqQuaterPeriodCode.getQuaterName());
        quater.setAlternativeName(reqQuaterPeriodCode.getAlternativeName());
        quater.setStatus(reqQuaterPeriodCode.getStatus());
        quaterRepo.save(quater);

        quaterPeriodResponseDto quaterDto = new quaterPeriodResponseDto();
        quaterDto.setQuaterPeriodCode(quater.getQuaterPeriodCode());
        quaterDto.setQuaterName(quater.getQuaterName());
        quaterDto.setAlternativeName(quater.getAlternativeName());
        quaterDto.setStatus(quater.getStatus());
        quaterDto.setCreatedDate(quater.getCreatedDate());
        quaterDto.setCreatedBy(quater.getCreatedBy());
        quaterDto.setModifiedDate(quater.getModifiedDate());
        quaterDto.setModifiedBy(quater.getModifiedBy());
        return ResponseEntity.ok(quaterDto);
    }

    public void deleteQuaterPeriod(String quaterPeriodCode) {

        quaterRepo.deleteById(quaterPeriodCode);
    }

    public Optional<QuaterPeriod> getQuaterPeriodById(String quaterPeriodCode) {
        return quaterRepo.findById(quaterPeriodCode);
    }

    public void updateQuaterPeriod(String quaterPeriodCode, QuaterPeriod reqQuaterPeriod) {
        quaterRepo.findById(quaterPeriodCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Quater Period  with Code " + quaterPeriodCode + " does not exist"));

        reqQuaterPeriod.setQuaterPeriodCode(quaterPeriodCode);
        QuaterPeriod org = new QuaterPeriod();
        org.setCreatedBy(reqQuaterPeriod.getCreatedBy());
        org.setCreatedDate(reqQuaterPeriod.getCreatedDate());
        org.setModifiedBy(reqQuaterPeriod.getModifiedBy());
        quaterRepo.save(reqQuaterPeriod);

    }

    public Optional<QuaterPeriod> getQuaterPeriodCode(String QuaterPeriodCode) {
        return quaterRepo.findById(QuaterPeriodCode);
    }

    public Map<String, Boolean> updateStatus(String officeID) {
        Optional<QuaterPeriod> bp = quaterRepo.findById(officeID);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        quaterRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

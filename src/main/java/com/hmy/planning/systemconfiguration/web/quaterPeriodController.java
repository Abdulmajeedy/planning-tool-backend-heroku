package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.quaterPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.quaterPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;
import com.hmy.planning.systemconfiguration.repository.QuaterPeriodRepository;
import com.hmy.planning.systemconfiguration.service.QuaterPeriodService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.quaterPeriodApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class quaterPeriodController implements quaterPeriodApi {

    private QuaterPeriodService quaterServices;
    private QuaterPeriodRepository quaterRepo;
    private ModelMapper modelMapper;

    @Autowired
    public quaterPeriodController(QuaterPeriodService quaterServices, ModelMapper modelMapper) {

        this.quaterServices = quaterServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<quaterPeriodResponseDto>> getQuaterPeriod(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<QuaterPeriod> activity = quaterServices.findAllQuaterPeriod(pageRequest);
        List<quaterPeriodResponseDto> actDto = new ArrayList<>();
        for (QuaterPeriod quat : activity) {
            quaterPeriodResponseDto responseDto = new quaterPeriodResponseDto();
            responseDto.setQuaterPeriodCode(quat.getQuaterPeriodCode());
            responseDto.setQuaterName(quat.getQuaterName());
            responseDto.setAlternativeName(quat.getAlternativeName());
            responseDto.setStatus(quat.getStatus());
            responseDto.setCreatedDate(quat.getCreatedDate());
            responseDto.setCreatedBy(quat.getCreatedBy());
            responseDto.setModifiedDate(quat.getModifiedDate());
            responseDto.setModifiedBy(quat.getModifiedBy());
            actDto.add(responseDto);
        }
        return ResponseEntity.ok(actDto);
    }

    @Override
    public ResponseEntity<quaterPeriodResponseDto> registerNewQuaterPeriod(quaterPeriodRequestDto reqQuaterPeriod) {
        return quaterServices.addNewQuaterPeriod(reqQuaterPeriod);
    }

    @Override
    public ResponseEntity<quaterPeriodResponseDto> getQuaterPeriodById(String reqQuaterPeriod) {
        Optional<QuaterPeriod> quater = quaterServices.getQuaterPeriodById(reqQuaterPeriod);
        if (!quater.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            QuaterPeriod quat = quater.get();
            quaterPeriodResponseDto responseDto = new quaterPeriodResponseDto();
            responseDto.setQuaterPeriodCode(quat.getQuaterPeriodCode());
            responseDto.setQuaterName(quat.getQuaterName());
            responseDto.setAlternativeName(quat.getAlternativeName());
            responseDto.setStatus(quat.getStatus());
            responseDto.setCreatedDate(quat.getCreatedDate());
            responseDto.setCreatedBy(quat.getCreatedBy());
            responseDto.setModifiedDate(quat.getModifiedDate());
            responseDto.setModifiedBy(quat.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String quaterPeriodCode) {
        quaterServices.deleteQuaterPeriod(quaterPeriodCode);
    }

    @Override
    public ResponseEntity updateQuaterPeriod(String quaterPeriodCode, QuaterPeriod reqQuaterPeriod) {
        Optional<QuaterPeriod> quater = quaterServices.getQuaterPeriodById(quaterPeriodCode);
        quaterServices.updateQuaterPeriod(quaterPeriodCode, reqQuaterPeriod);
        QuaterPeriod quat = quater.get();
        quaterPeriodResponseDto responseDto = new quaterPeriodResponseDto();
        responseDto.setQuaterPeriodCode(quat.getQuaterPeriodCode());
        responseDto.setQuaterName(quat.getQuaterName());
        responseDto.setAlternativeName(quat.getAlternativeName());
        responseDto.setStatus(quat.getStatus());
        responseDto.setCreatedDate(quat.getCreatedDate());
        responseDto.setCreatedBy(quat.getCreatedBy());
        responseDto.setModifiedDate(quat.getModifiedDate());
        responseDto.setModifiedBy(quat.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity updateStatus(String quaterPeriodCode) {
        return ResponseEntity.ok().body(quaterServices.updateStatus(quaterPeriodCode));
    }
}

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
        return ResponseEntity.ok(quaterServices.findAllQuaterPeriod(page, size));

    }

    @Override
    public ResponseEntity<quaterPeriodResponseDto> registerNewQuaterPeriod(quaterPeriodRequestDto reqQuaterPeriod) {
        return quaterServices.addNewQuaterPeriod(reqQuaterPeriod);
    }

    @Override
    public ResponseEntity<quaterPeriodResponseDto> getQuaterPeriodById(String reqQuaterPeriod) {
        return ResponseEntity.ok(quaterServices.getQuaterPeriodById(reqQuaterPeriod));
    }

    @Override
    public void deleteById(String quaterPeriodCode) {
        quaterServices.deleteQuaterPeriod(quaterPeriodCode);
    }

    @Override
    public ResponseEntity updateQuaterPeriod(String quaterPeriodCode, QuaterPeriod reqQuaterPeriod) {
        return ResponseEntity.ok(quaterServices.updateQuaterPeriod(quaterPeriodCode, reqQuaterPeriod));
    }

    @Override
    public ResponseEntity updateStatus(String quaterPeriodCode) {
        return ResponseEntity.ok().body(quaterServices.updateStatus(quaterPeriodCode));
    }
}

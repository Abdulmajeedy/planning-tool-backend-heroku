package com.hmy.planning.systemconfiguration.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hmy.planning.systemconfiguration.dto.impPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.impPeriodResponseDto;
import com.hmy.planning.systemconfiguration.repository.implPeriodRepository;
import com.hmy.planning.systemconfiguration.service.impPeriodService;
import com.hmy.planning.systemconfiguration.web.api.impPeriodApi;

@RestController
public class impPeriodController implements impPeriodApi {

    private impPeriodService impService;
    private implPeriodRepository impPeriodRepo;
    private ModelMapper modelMapper;

    @Autowired
    public impPeriodController(impPeriodService impService, ModelMapper modelMapper) {

        this.impService = impService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<impPeriodResponseDto>> getimpPeriode(int page, int size) {
        return ResponseEntity.ok(impService.findAllImplementatedPeriod(page, size));
    }

    @Override
    public ResponseEntity<impPeriodResponseDto> registerNewimpPeriode(impPeriodRequestDto impPeriode) {
        return impService.addNewImpPeriod(impPeriode);
    }

    @Override
    public ResponseEntity<impPeriodResponseDto> getimpPeriodeById(String impPeriodCode) {
        return ResponseEntity.ok(impService.getImpPeriodById(impPeriodCode));
    }

    @Override
    public void deleteById(String impPeriodCode) {
        impService.deleteImpPeriod(impPeriodCode);

    }

    @Override
    public void deleteAll() {
        impPeriodRepo.deleteAll();
    }

    @Override
    public ResponseEntity updateimpPeriode(String impPeriodCode, impPeriodRequestDto reqImp) {
        return impService.updateImpPeriod(impPeriodCode, reqImp);
    }

    @Override
    public ResponseEntity updateStatus(String impPeriodCode) {
        return ResponseEntity.ok().body(impService.updateStatus(impPeriodCode));
    }

}

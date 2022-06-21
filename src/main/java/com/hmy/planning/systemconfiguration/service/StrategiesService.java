package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.strategiesRequestDto;
import com.hmy.planning.systemconfiguration.dto.strategiesResponseDto;
import com.hmy.planning.systemconfiguration.models.Objectives;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.repository.StrategiesRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class StrategiesService {

    @Autowired
    private StrategiesRepository strategyRepo;
    private final ModelMapper modelmapper;

    public List<strategiesResponseDto> findAllStrategies(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Strategies> strtz = strategyRepo.findAll(pageRequest).getContent();
        List<strategiesResponseDto> objDto = new ArrayList<>();
        for (Strategies strg : strtz) {
            strategiesResponseDto responseDto = modelmapper.map(strg, strategiesResponseDto.class);
            objDto.add(responseDto);
        }
        return objDto;
    }

    public ResponseEntity<strategiesResponseDto> addNewStrategies(strategiesRequestDto reqStrategies) {
        Strategies strategy = modelmapper.map(reqStrategies, Strategies.class);
        strategyRepo.save(strategy);

        strategiesResponseDto obj = modelmapper.map(strategy, strategiesResponseDto.class);
        return ResponseEntity.ok(obj);
    }

    public void deleteStrategies(String strategyCode) {

        strategyRepo.deleteById(strategyCode);
    }

    public strategiesResponseDto getStrategiesById(String strategyCode) {
        Optional<Strategies> strt = strategyRepo.findById(strategyCode);
        if (!strt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + strategyCode + "is not Found");
        } else {
            Strategies obj = strt.get();
            strategiesResponseDto responseDto = modelmapper.map(obj, strategiesResponseDto.class);
            return responseDto;
        }
    }

    public ResponseEntity<strategiesResponseDto> updateStrategies(String strategyCode, Strategies reqStrategies) {

        Optional<Strategies> stra = strategyRepo.findById(strategyCode);
        if (!stra.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + strategyCode + "is not Found");
        }
        Strategies obctc = modelmapper.map(reqStrategies, Strategies.class);
        obctc.setStrategyCode(strategyCode);
        strategyRepo.save(obctc);

        strategiesResponseDto roltDto = modelmapper.map(obctc, strategiesResponseDto.class);
        return ResponseEntity.ok(roltDto);
    }

    public Optional<Strategies> getStrategiesCode(String strategyCode) {
        return strategyRepo.findById(strategyCode);
    }

    public Map<String, Boolean> updateStatus(String officeID) {
        Optional<Strategies> bp = strategyRepo.findById(officeID);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        strategyRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

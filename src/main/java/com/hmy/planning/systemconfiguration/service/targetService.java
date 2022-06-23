package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.targetRequestDto;
import com.hmy.planning.systemconfiguration.dto.targetResponseDto;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.models.Target;
import com.hmy.planning.systemconfiguration.repository.StrategiesRepository;
import com.hmy.planning.systemconfiguration.repository.TargetRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Service
@Slf4j
public class targetService {
    @Autowired
    private final TargetRepository targetRepo;

    @Autowired
    private final StrategiesService strategiesService;
    private final StrategiesRepository strategiesRepo;
    private final QuaterPeriodService quaterPd;

    private final ModelMapper modelmapper;

    public List<targetResponseDto> findAllTarget(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Target> obj = targetRepo.findAll(pageRequest).getContent();
        List<targetResponseDto> trgDto = new ArrayList<>();
        for (Target target : obj) {
            targetResponseDto responseDto = modelmapper.map(target, targetResponseDto.class);
            responseDto.setStrategyCode(target.getStrategies().getStrategyCode());
            trgDto.add(responseDto);
        }
        return trgDto;
    }

    public void deleteTarget(String targetCode) {

        targetRepo.deleteById(targetCode);
    }

    public void deleteAllStrategies() {

        targetRepo.deleteAll();
    }

    public ResponseEntity<targetResponseDto> addNewTarget(targetRequestDto reqTarget) {
        Optional<Strategies> str = strategiesRepo.findById(reqTarget.getStrategyCode());
        log.info(reqTarget.getStrategyCode());
        Strategies strategyObj = new Strategies();
        strategyObj.setStrategy(reqTarget.getStrategyCode());

        Target target = modelmapper.map(reqTarget, Target.class);
        target.setStrategies(strategyObj);
        targetRepo.save(target);

        targetResponseDto obj = modelmapper.map(target, targetResponseDto.class);
        obj.setStrategyCode(target.getStrategies().getStrategyCode());
        return ResponseEntity.ok(obj);
        // havmleqzorovbsrl
    }

    public targetResponseDto getTargetById(String targetCode) {
        Optional<Target> target = targetRepo.findById(targetCode);
        if (!target.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Target  with this code" + targetCode + "is not Found");
        } else {
            Target trgobj = target.get();
            targetResponseDto responseDto = modelmapper.map(trgobj, targetResponseDto.class);
            responseDto.setStrategyCode(trgobj.getStrategies().getStrategyCode());
            return responseDto;
        }
    }

    public ResponseEntity<targetResponseDto> updateTarget(String targetCode, Target reqTarget) {
        Optional<Target> objective = targetRepo.findById(targetCode);
        if (!objective.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Target  with this code" + targetCode + "is not Found");
        }
        Target target = modelmapper.map(reqTarget, Target.class);
        target.setTargetCode(targetCode);
        targetRepo.save(target);

        targetResponseDto roltDto = modelmapper.map(target, targetResponseDto.class);
        return ResponseEntity.ok(roltDto);
    }

    public Optional<Target> getTargetCode(String targetCode) {
        return targetRepo.findById(targetCode);
    }

    public Map<String, Boolean> updateStatus(String targetCode) {
        Optional<Target> bp = targetRepo.findById(targetCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        targetRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

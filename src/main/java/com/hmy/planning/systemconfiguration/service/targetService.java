package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.targetRequestDto;
import com.hmy.planning.systemconfiguration.dto.targetResponseDto;
import com.hmy.planning.systemconfiguration.models.Strategies;
import com.hmy.planning.systemconfiguration.models.Target;
import com.hmy.planning.systemconfiguration.repository.TargetRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class targetService {
    @Autowired
    private final TargetRepository targetRepo;

    @Autowired
    private final StrategiesService strategiesService;
    private final QuaterPeriodService quaterPd;

    private final ModelMapper modelMapper;

    public List<Target> findAllTarget(PageRequest pageRequest) {
        return targetRepo.findAll(pageRequest).getContent();
    }

    public void deleteTarget(String targetCode) {

        targetRepo.deleteById(targetCode);
    }

    public ResponseEntity<targetResponseDto> addNewTarget(targetRequestDto reqTarget) {
        Optional<Strategies> strategies = strategiesService.getStrategiesCode(reqTarget.getStrategyCode());

        if (!strategies.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Strategies Str = strategies.get();
        Target tar = new Target();

        tar.setTargetName(reqTarget.getTargetName());
        tar.setStrategies(Str);
        tar.setStatus(reqTarget.getStatus());
        targetRepo.save(tar);

        targetResponseDto targetDto = new targetResponseDto();
        targetDto.setTargetCode(tar.getTargetCode());
        targetDto.setTargetName(tar.getTargetName());
        targetDto.setStrategyCode(tar.getStrategies().getStrategyCode());
        targetDto.setStatus(tar.getStatus());
        targetDto.setCreatedDate(tar.getCreatedDate());
        targetDto.setCreatedBy(tar.getCreatedBy());
        targetDto.setModifiedDate(tar.getModifiedDate());
        targetDto.setModifiedBy(tar.getModifiedBy());
        return ResponseEntity.ok(targetDto);
    }

    public Optional<Target> getTargetById(String targetCode) {
        return targetRepo.findById(targetCode);
    }

    public void updateTarget(String targetCode,
            Target reqTarget) {
        targetRepo.findById(
                targetCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Target   with Code " + targetCode + " does not exist"));

        reqTarget.setTargetCode(targetCode);
        Target acti = new Target();
        acti.setCreatedBy(reqTarget.getCreatedBy());
        acti.setCreatedDate(reqTarget.getCreatedDate());
        acti.setStrategies(reqTarget.getStrategies());
        acti.setModifiedBy(reqTarget.getModifiedBy());
        targetRepo.save(reqTarget);
    }

    public Optional<Target> getTargetCode(String targetCode) {
        return targetRepo.findById(targetCode);
    }

}

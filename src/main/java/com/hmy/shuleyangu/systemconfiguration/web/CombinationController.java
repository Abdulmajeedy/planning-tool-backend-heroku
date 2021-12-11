package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.CombinationRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.CombinationResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Combination;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.repository.CombinationRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import com.hmy.shuleyangu.systemconfiguration.service.CombinationService;
import com.hmy.shuleyangu.systemconfiguration.service.EducationLevelService;
import com.hmy.shuleyangu.systemconfiguration.service.RegionService;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import com.hmy.shuleyangu.systemconfiguration.web.api.CombinationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CombinationController implements CombinationApi {
    @Autowired
    private CombinationService combinationService;
    @Autowired
    private CombinationRepository combinationRepository;
    @Autowired
    private EducationLevelService educationLevelService;

    public CombinationController(CombinationService combinationService,EducationLevelService educationLevelService){
        this.combinationService=combinationService;
        this.educationLevelService=educationLevelService;
    }
    public ResponseEntity<List<CombinationResponseDto>> getCombinations(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Combination> combinations = combinationService.findAllCombinations(pageRequest);

        List<CombinationResponseDto> comb = new ArrayList<>();
        for(Combination c:combinations)
        {
            CombinationResponseDto responseDto = new CombinationResponseDto();
            responseDto.setCombinationId(c.getCombinationId());
            responseDto.setCombinationName(c.getCombinationName());
            responseDto.setDescription(c.getDescription());
            responseDto.setNectaCode(c.getNectaCode());
            responseDto.setStatus(c.getStatus());
            responseDto.setCreatedDate(c.getCreatedDate());
            responseDto.setCreatedBy(c.getCreatedBy());
            responseDto.setModifiedDate(c.getModifiedDate());
            responseDto.setModifiedBy(c.getModifiedBy());
            responseDto.setEducationLevelId(c.getEducationLevel().getEducationLevelId());
            comb.add(responseDto);
        }
        return ResponseEntity.ok(comb);

    }

    @Override
    public ResponseEntity<CombinationResponseDto> registerNewCombination(CombinationRequestDto combinationRequestDto) {
        return combinationService.addNewCombination(combinationRequestDto);
    }


}

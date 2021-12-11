package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.CombinationRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.CombinationResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Combination;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import com.hmy.shuleyangu.systemconfiguration.repository.CombinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CombinationService {
    @Autowired
    private CombinationRepository combinationRepository;
    @Autowired
    private EducationLevelService educationLevelService;


    @Autowired
    public CombinationService(CombinationRepository combinationRepository) {
        this.combinationRepository = combinationRepository;
    }

    public List<Combination> findAllCombinations(PageRequest pageRequest) {
        return combinationRepository.findAll(pageRequest).getContent();
    }

    public Optional<Combination> getCombinationById(String combinationId){
        return combinationRepository.findById(combinationId);
    }


    public ResponseEntity<CombinationResponseDto> addNewCombination(CombinationRequestDto combinationDto) {
        Optional<EducationLevel> el = educationLevelService.getEducationLevelById(combinationDto.getEducationLevelId());
        if(!el.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Combination c = new Combination();
        EducationLevel e = el.get();

        c.setEducationLevel(e);
        c.setCombinationName(combinationDto.getCombinationName());
        c.setDescription(combinationDto.getDescription());
        c.setNectaCode(combinationDto.getNectaCode());
        c.setStatus(combinationDto.getStatus());

        combinationRepository.save(c);

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
        responseDto.setEducationLevelId(e.getEducationLevelId());
        return  ResponseEntity.ok(responseDto);
    }

    public void deleteCombination(String combinationId){
        combinationRepository.deleteById(combinationId);
    }

}

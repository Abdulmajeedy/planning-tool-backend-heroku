package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.CleanessRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.CleanessResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Cleaness;
import com.hmy.shuleyangu.systemconfiguration.repository.CleanessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CleanessService {
    @Autowired
    private CleanessRepository cleanessRepository;

    @Autowired
    public CleanessService(CleanessRepository cleanessRepository){
        this.cleanessRepository =cleanessRepository;
    }

    public List<Cleaness> findAllCleaness(PageRequest pageRequest) {
        return cleanessRepository.findAll(pageRequest).getContent();
    }

    public Optional<Cleaness> getCleanessById(String cleanessId){

        return cleanessRepository.findById(cleanessId);
    }
    public ResponseEntity<CleanessResponseDto> addNewCleaness(CleanessRequestDto crd) {
        Cleaness c = new Cleaness();
        c.setRemark(crd.getRemark());
        c.setRate(crd.getRate());
        cleanessRepository.save(c);

        CleanessResponseDto responseDto = new CleanessResponseDto();
        responseDto.setCleanessId(c.getCleanessId());
        responseDto.setRemark(c.getRemark());
        responseDto.setRate(c.getRate());
        responseDto.setCreatedDate(c.getCreatedDate());
        responseDto.setCreatedBy(c.getCreatedBy());
        responseDto.setModifiedDate(c.getModifiedDate());
        responseDto.setModifiedBy(c.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}

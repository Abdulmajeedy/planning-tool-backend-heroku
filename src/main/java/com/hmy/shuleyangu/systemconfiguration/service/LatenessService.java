package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.DisplineRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.DisplineResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.LatenessRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.LatenessResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Discpline;
import com.hmy.shuleyangu.systemconfiguration.models.Lateness;
import com.hmy.shuleyangu.systemconfiguration.repository.LatenessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LatenessService {
    @Autowired
    private LatenessRepository latenessRepository;
    @Autowired
    public LatenessService(LatenessRepository latenessRepository){
        this.latenessRepository = latenessRepository;
    }
    public List<Lateness> findLateness(PageRequest pageRequest) {
        return latenessRepository.findAll(pageRequest).getContent();
    }
    public ResponseEntity<LatenessResponseDto> addLateness(LatenessRequestDto late) {
        Lateness l = new Lateness();
        l.setRemark(late.getRemark());
        l.setRate(late.getRate());
        latenessRepository.save(l);

        LatenessResponseDto responseDto = new LatenessResponseDto();
        responseDto.setLatenessId(l.getLatenessId());
        responseDto.setRemark(l.getRemark());
        responseDto.setRate(l.getRate());
        responseDto.setCreatedDate(l.getCreatedDate());
        responseDto.setCreatedBy(l.getCreatedBy());
        responseDto.setModifiedDate(l.getModifiedDate());
        responseDto.setModifiedBy(l.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}

package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.DisplineResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.LatenessRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.LatenessResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Lateness;
import com.hmy.shuleyangu.systemconfiguration.repository.LatenessRepository;
import com.hmy.shuleyangu.systemconfiguration.service.LatenessService;
import com.hmy.shuleyangu.systemconfiguration.web.api.LatenessApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LatenessController implements LatenessApi {
    @Autowired
    private LatenessRepository latenessRepository;
    @Autowired
    private LatenessService latenessService;

    public LatenessController(LatenessService latenessService){
        this.latenessService = latenessService;
    }

    @Override
    public ResponseEntity<List<LatenessResponseDto>> getLateness(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Lateness> latenesses = latenessService.findLateness(pageRequest);
        List<LatenessResponseDto> lte = new ArrayList<>();
        for(Lateness l:latenesses)
        {
            LatenessResponseDto responseDto = new LatenessResponseDto();
            responseDto.setRemark(l.getRemark());
            responseDto.setRate(l.getRate());
            responseDto.setCreatedDate(l.getCreatedDate());
            responseDto.setCreatedBy(l.getCreatedBy());
            responseDto.setModifiedDate(l.getModifiedDate());
            responseDto.setModifiedBy(l.getModifiedBy());
            lte.add(responseDto);
        }
        return ResponseEntity.ok(lte);
    }

    @Override
    public ResponseEntity<LatenessResponseDto> registerNewLateness(LatenessRequestDto late) {
        return latenessService.addLateness(late);

    }
}

package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.DisplineRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.DisplineResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Discpline;
import com.hmy.shuleyangu.systemconfiguration.repository.DisplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscplineService {
    @Autowired
    private DisplineRepository displineRepository;
    @Autowired
    public DiscplineService(DisplineRepository displineRepository){
        this.displineRepository = displineRepository;
    }

    public List<Discpline> findAllDisplines(PageRequest pageRequest) {
        return displineRepository.findAll(pageRequest).getContent();
    }
    public ResponseEntity<DisplineResponseDto> addNewDispline(DisplineRequestDto dis) {
        Discpline d = new Discpline();
        d.setRemark(dis.getRemark());
        d.setRate(dis.getRate());
        displineRepository.save(d);

        DisplineResponseDto responseDto = new DisplineResponseDto();
        responseDto.setDisplineId(d.getDisplineId());
        responseDto.setRemark(d.getRemark());
        responseDto.setRate(d.getRate());
        responseDto.setCreatedDate(d.getCreatedDate());
        responseDto.setCreatedBy(d.getCreatedBy());
        responseDto.setModifiedDate(d.getModifiedDate());
        responseDto.setModifiedBy(d.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}

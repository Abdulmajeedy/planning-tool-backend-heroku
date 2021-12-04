package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.Extracariculum;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ExtracariculumRepository;
import com.hmy.shuleyangu.systemconfiguration.service.ExtracariculumService;
import com.hmy.shuleyangu.systemconfiguration.web.api.ExtracaricullumApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExtracariculumController implements ExtracaricullumApi {
    @Autowired
    private ExtracariculumService extracariculumService;
    @Autowired
    private ExtracariculumRepository extracariculumRepository;

    public ExtracariculumController(ExtracariculumService extracariculumService){
        this.extracariculumService = extracariculumService;
    }

    public ResponseEntity<List<ExtracariculumResponseDto>> getExtraCariculums(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Extracariculum> extracariculums = extracariculumService.findAllExtracariculum(pageRequest);
        List<ExtracariculumResponseDto> ext = new ArrayList<>();
        for(Extracariculum e:extracariculums)
        {
            ExtracariculumResponseDto responseDto = new ExtracariculumResponseDto();
            responseDto.setRemark(e.getRemark());
            responseDto.setRate(e.getRate());
            responseDto.setCreatedDate(e.getCreatedDate());
            responseDto.setCreatedBy(e.getCreatedBy());
            responseDto.setModifiedDate(e.getModifiedDate());
            responseDto.setModifiedBy(e.getModifiedBy());
            ext.add(responseDto);
        }
        return ResponseEntity.ok(ext);
    }

    @Override
    public ResponseEntity<ExtracariculumResponseDto> registerNewExtracurricular(ExtracariculumRequestDto erd) {
        return extracariculumService.addNewExtracaricular(erd);
    }



}

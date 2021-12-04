package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.DisplineRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.DisplineResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Discpline;
import com.hmy.shuleyangu.systemconfiguration.repository.DisplineRepository;
import com.hmy.shuleyangu.systemconfiguration.service.DiscplineService;
import com.hmy.shuleyangu.systemconfiguration.web.api.DisciplineApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DisciplineController implements DisciplineApi {
    @Autowired
    private DisplineRepository displineRepository;
    @Autowired
    private DiscplineService disciplineService;

    public DisciplineController(DiscplineService disciplineService){
        this.disciplineService = disciplineService;
    }

    public ResponseEntity<List<DisplineResponseDto>> getDiscipline(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Discpline> disciplines = disciplineService.findAllDisplines(pageRequest);
        List<DisplineResponseDto> dis = new ArrayList<>();
        for(Discpline d:disciplines)
        {
            DisplineResponseDto responseDto = new DisplineResponseDto();
            responseDto.setDisplineId(d.getDisplineId());
            responseDto.setRemark(d.getRemark());
            responseDto.setRate(d.getRate());
            responseDto.setCreatedDate(d.getCreatedDate());
            responseDto.setCreatedBy(d.getCreatedBy());
            responseDto.setModifiedDate(d.getModifiedDate());
            responseDto.setModifiedBy(d.getModifiedBy());
            dis.add(responseDto);
        }
        return ResponseEntity.ok(dis);
    }
    @Override
    public ResponseEntity<DisplineResponseDto> registerNewDiscipline(DisplineRequestDto dis) {
        return disciplineService.addNewDispline(dis);
    }
}

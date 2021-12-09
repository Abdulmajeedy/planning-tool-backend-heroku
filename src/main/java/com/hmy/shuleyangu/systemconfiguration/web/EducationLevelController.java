package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.EducationLevelRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.EducationLevelResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.EducationLevel;
import com.hmy.shuleyangu.systemconfiguration.repository.EducationLevelRepository;
import com.hmy.shuleyangu.systemconfiguration.service.EducationLevelService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.EducationLevelApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EducationLevelController implements EducationLevelApi {
    @Autowired
    private EducationLevelRepository educationLevelRepository;
    @Autowired
    private EducationLevelService educationLevelService;

    public EducationLevelController(EducationLevelService educationLevelService){
        this.educationLevelService = educationLevelService;
    }

    public ResponseEntity<List<EducationLevelResponseDto>> getEducationLevels(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<EducationLevel> educationLevels = educationLevelService.findEducationLevels(pageRequest);
        List<EducationLevelResponseDto> elr = new ArrayList<>();
        for(EducationLevel e:educationLevels)
        {
            EducationLevelResponseDto responseDto = new EducationLevelResponseDto();
            responseDto.setEducationLevelId(e.getEducationLevelId());
            responseDto.setEducationLevelName(e.getEducationLevelName());
            responseDto.setDescription(e.getDescription());
            responseDto.setStatus(e.getStatus());
            responseDto.setCreatedDate(e.getCreatedDate());
            responseDto.setCreatedBy(e.getCreatedBy());
            responseDto.setModifiedDate(e.getModifiedDate());
            responseDto.setModifiedBy(e.getModifiedBy());
            elr.add(responseDto);
        }
        return ResponseEntity.ok(elr);
    }

    @Override
    public ResponseEntity<EducationLevelResponseDto> getEducationLevelById(String educationLevelId)
    {
        Optional<EducationLevel> el = educationLevelService.getEducationLevelById(educationLevelId);
        if(!el.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
        }
        else
        {
            EducationLevel e = el.get();
            EducationLevelResponseDto responseDto = new EducationLevelResponseDto();
            responseDto.setEducationLevelId(e.getEducationLevelId());
            responseDto.setEducationLevelName(e.getEducationLevelName());
            responseDto.setDescription(e.getDescription());
            responseDto.setStatus(e.getStatus());
            responseDto.setCreatedDate(e.getCreatedDate());
            responseDto.setCreatedBy(e.getCreatedBy());
            responseDto.setModifiedDate(e.getModifiedDate());
            responseDto.setModifiedBy(e.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public ResponseEntity<EducationLevelResponseDto> registerNewEducationLevels(EducationLevelRequestDto elr) {
            return educationLevelService.addNewEducationLevel(elr);
    }

    @Override
    public void updateEducationLevel(String educationLevelId, EducationLevel educationLevel){
        educationLevelService.updateEducationLevel(educationLevelId,educationLevel);

    }
}


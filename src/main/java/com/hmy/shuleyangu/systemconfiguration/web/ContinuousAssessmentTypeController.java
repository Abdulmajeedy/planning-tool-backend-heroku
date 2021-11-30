package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessReqDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessRespDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.ContAssessmentType;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.ContAssesTypeService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.ContAssTypeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContinuousAssessmentTypeController implements ContAssTypeApi {
    private final ContAssesTypeService continuousAssessmentTypeService;

    @Autowired
    public ContinuousAssessmentTypeController(ContAssesTypeService continuousAssessmentTypeService){

        this.continuousAssessmentTypeService=continuousAssessmentTypeService;
    }

    public ResponseEntity<List<ContAssessRespDto>> getContAssessment(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<ContAssessmentType> contAssessmentTypes = continuousAssessmentTypeService.findAllContAssessTypes(pageRequest);

        List<ContAssessRespDto> contAss = new ArrayList<>();
        for(ContAssessmentType c:contAssessmentTypes)
        {
            ContAssessRespDto contAssDto = new ContAssessRespDto();
            contAssDto.setContAssessmentTypeId(c.getContAssessmentTypeId());
            contAssDto.setContAssessmentTypeName(c.getContAssessmentTypeName());
            contAssDto.setStatus(c.getStatus());
            contAssDto.setCreatedDate(c.getCreatedDate());
            contAssDto.setCreatedBy(c.getCreatedBy());
            contAssDto.setModifiedDate(c.getModifiedDate());
            contAssDto.setModifiedBy(c.getModifiedBy());
            contAss.add(contAssDto);
        }
        return ResponseEntity.ok(contAss);

    }
    public ContAssessRespDto registerContAssType(ContAssessReqDto contAssReq){
        return continuousAssessmentTypeService.addNewContAssessType(contAssReq);
    }

    public ResponseEntity<ContAssessRespDto> getContAssessTypeById(String contAssTypeId)
    {
        Optional<ContAssessmentType> ca = continuousAssessmentTypeService.getContAssessTypeById(contAssTypeId);
        if(!ca.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
        }
        else
        {
            ContAssessmentType c = ca.get();
            ContAssessRespDto responseDto = new ContAssessRespDto();
            responseDto.setContAssessmentTypeId(c.getContAssessmentTypeId());
            responseDto.setContAssessmentTypeName(c.getContAssessmentTypeName());
            responseDto.setStatus(c.getStatus());
            responseDto.setCreatedDate(c.getCreatedDate());
            responseDto.setCreatedBy(c.getCreatedBy());
            responseDto.setModifiedDate(c.getModifiedDate());
            responseDto.setModifiedBy(c.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    public void deleteById(String contAssTypeId){

        continuousAssessmentTypeService.deleteContAssesType(contAssTypeId);
    }

    public ResponseEntity updateContAssType(String contAssTypeId, ContAssessmentType cat){
        Optional<ContAssessmentType> ca = continuousAssessmentTypeService.getContAssesTypeById(contAssTypeId);
        continuousAssessmentTypeService.updateContAssType(contAssTypeId,cat);
        ContAssessmentType c = ca.get();
        ContAssessRespDto responseDto = new ContAssessRespDto();
        responseDto.setContAssessmentTypeId((contAssTypeId));
        responseDto.setContAssessmentTypeName(c.getContAssessmentTypeName());
        responseDto.setStatus(c.getStatus());
        responseDto.setModifiedDate(c.getModifiedDate());
        responseDto.setModifiedBy(c.getModifiedBy());
        responseDto.setCreatedDate(c.getCreatedDate());
        responseDto.setCreatedBy(c.getCreatedBy());
        return ResponseEntity.ok(responseDto);
    }
}

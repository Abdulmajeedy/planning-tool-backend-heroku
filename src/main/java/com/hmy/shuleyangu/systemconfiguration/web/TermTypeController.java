package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import com.hmy.shuleyangu.systemconfiguration.service.TermTypeService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.TermTypeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TermTypeController implements TermTypeApi {
    private final TermTypeService termTypeService;

    @Autowired
    public TermTypeController(TermTypeService termTypeService){
        this.termTypeService=termTypeService;
    }

    public ResponseEntity<List<TermTypeResponseDto>> getTermTypes(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<TermTypes> termTypes = termTypeService.findAllTermTypes(pageRequest);
        List<TermTypeResponseDto> tt = new ArrayList<>();
        for(TermTypes t:termTypes)
        {
            TermTypeResponseDto responseDto = new TermTypeResponseDto();
            responseDto.setTermID(t.getTermID());
            responseDto.setTermTypeName(t.getTermTypeName());
            responseDto.setNumberOfTerms(t.getNumberOfTerms());
            responseDto.setStatus(t.getStatus());
            responseDto.setCreatedDate(t.getCreatedDate());
            responseDto.setCreatedBy(t.getCreatedBy());
            responseDto.setModifiedDate(t.getModifiedDate());
            responseDto.setModifiedBy(t.getModifiedBy());
            tt.add(responseDto);
        }
        return ResponseEntity.ok(tt);
    }

    public ResponseEntity<TermTypeResponseDto> registerNewTermType(TermTypeRequestDto termTypeRequestDto)
    {
        return termTypeService.addNewTermType(termTypeRequestDto);

    }

    public ResponseEntity<TermTypeResponseDto> getTermTypeById(String termTypeId)
    {
        Optional<TermTypes> tt = termTypeService.getTermTypeById(termTypeId);
        if(!tt.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
        }
        else
        {
            TermTypes t = tt.get();
            TermTypeResponseDto responseDto = new TermTypeResponseDto();
            responseDto.setTermID(t.getTermID());
            responseDto.setTermTypeName(t.getTermTypeName());
            responseDto.setTermTypeName(t.getTermTypeName());
            responseDto.setStatus(t.getStatus());
            responseDto.setCreatedDate(t.getCreatedDate());
            responseDto.setCreatedBy(t.getCreatedBy());
            responseDto.setModifiedDate(t.getModifiedDate());
            responseDto.setModifiedBy(t.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    public void deleteById(String termTypeId){
        termTypeService.deleteTermType(termTypeId);
    }

    public ResponseEntity updateTermType(String termTypeId, TermTypes termToUpdate){
        Optional<TermTypes> tt = termTypeService.getTermTypeById(termTypeId);
        termTypeService.updateTermType(termTypeId,termToUpdate);
        TermTypes t = tt.get();

        TermTypeResponseDto responseDto = new TermTypeResponseDto();
        responseDto.setTermID(t.getTermID());
        responseDto.setTermTypeName(t.getTermTypeName());
        responseDto.setNumberOfTerms(t.getNumberOfTerms());
        responseDto.setStatus(t.getStatus());
        responseDto.setCreatedDate(t.getCreatedDate());
        responseDto.setCreatedBy(t.getCreatedBy());
        responseDto.setModifiedDate(t.getModifiedDate());
        responseDto.setModifiedBy(t.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}

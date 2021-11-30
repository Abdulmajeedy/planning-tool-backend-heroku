package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.TermTypeRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.TermTypeResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.TermTypeRepository;
import com.hmy.shuleyangu.systemconfiguration.web.api.TermTypeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TermTypeService {
    @Autowired
    private TermTypeRepository termTypeRepository;

    @Autowired
    public TermTypeService(TermTypeRepository termTypeRepository){
        this.termTypeRepository = termTypeRepository;
    }

    public List<TermTypes> findAllTermTypes(PageRequest pageRequest) {
        return termTypeRepository.findAll(pageRequest).getContent();
    }

    public ResponseEntity<TermTypeResponseDto> addNewTermType(TermTypeRequestDto tt) {
        TermTypes t = new TermTypes();
        t.setTermTypeName(tt.getTermTypeName());
        t.setNumberOfTerms(tt.getNumberOfTerms());
        t.setStatus(tt.getStatus());
        termTypeRepository.save(t);

        TermTypeResponseDto responseDto = new TermTypeResponseDto();
        responseDto.setTermID(t.getTermID());
        responseDto.setTermTypeName(t.getTermTypeName());
        responseDto.setNumberOfTerms(t.getNumberOfTerms());
        responseDto.setStatus(t.getStatus());
        return ResponseEntity.ok(responseDto);
    }

    public Optional<TermTypes> getTermTypeById(String termTypeId){

        return termTypeRepository.findById(termTypeId);
    }

    public void deleteTermType(String termTypeId){
        termTypeRepository.deleteById(termTypeId);
    }
    public void updateTermType(String termTypeId, TermTypes termToUpdate) {
    }
}

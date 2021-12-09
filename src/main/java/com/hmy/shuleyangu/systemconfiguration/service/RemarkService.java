package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.RemarkRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RemarkResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Remarks;
import com.hmy.shuleyangu.systemconfiguration.repository.RemarkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemarkService {
    private ModelMapper modelMapper;
    @Autowired
    private RemarkRepository remarkRepository;
    public RemarkService(ModelMapper modelMapper, RemarkRepository remarkRepository){
        this.modelMapper = modelMapper;
        this.remarkRepository = remarkRepository;
    }
    public List<Remarks> findRemarks(PageRequest pageRequest) {
        return remarkRepository.findAll(pageRequest).getContent();
    }
    public ResponseEntity<RemarkResponseDto> addNewRemark(RemarkRequestDto remarkRequestDto) {
        Remarks remarks = new Remarks();
        remarks.setRemarkName(remarkRequestDto.getRemarkName());
        remarkRepository.save(remarks);

        RemarkResponseDto responseDto = new RemarkResponseDto();
        responseDto.setRemarkId(remarks.getRemarkId());
        responseDto.setRemarkName(remarks.getRemarkName());
        responseDto.setCreatedDate(remarks.getCreatedDate());
        responseDto.setCreatedBy(remarks.getCreatedBy());
        responseDto.setModifiedDate(remarks.getModifiedDate());
        responseDto.setModifiedBy(remarks.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    public Optional<Remarks> getRemarkById(String remarkId){
        return remarkRepository.findById(remarkId);
    }

    public void deleteRemark(String remarkId){
        remarkRepository.deleteById(remarkId);
    }

}


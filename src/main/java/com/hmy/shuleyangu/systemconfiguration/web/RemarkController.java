package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.RemarkRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RemarkResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Remarks;
import com.hmy.shuleyangu.systemconfiguration.repository.RemarkRepository;
import com.hmy.shuleyangu.systemconfiguration.service.RemarkService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.RemarkApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RemarkController implements RemarkApi {
    @Autowired
    private RemarkService remarkService;
    @Autowired
    private RemarkRepository remarkRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RemarkController(RemarkService remarkService, ModelMapper modelMapper){
        this.remarkService = remarkService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<RemarkResponseDto>> getRemarks(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Remarks> remarks = remarkService.findRemarks(pageRequest);
        List<RemarkResponseDto> remarkResponseDto = new ArrayList<>();
        for(Remarks re:remarks)
        {
            RemarkResponseDto responseDto = new RemarkResponseDto();
            responseDto.setRemarkId(re.getRemarkId());
            responseDto.setRemarkName(re.getRemarkName());
            responseDto.setCreatedDate(re.getCreatedDate());
            responseDto.setCreatedBy(re.getCreatedBy());
            responseDto.setModifiedDate(re.getModifiedDate());
            responseDto.setModifiedBy(re.getModifiedBy());
            remarkResponseDto.add(responseDto);
        }
        return ResponseEntity.ok(remarkResponseDto);
    }

    public ResponseEntity<RemarkResponseDto> registerNewRemark(RemarkRequestDto remarkRequestDto){
        return remarkService.addNewRemark(remarkRequestDto);
    }

    public ResponseEntity<RemarkResponseDto> getRemarkById(String remarkId) {
        {
            Optional<Remarks> re = remarkService.getRemarkById(remarkId);
            if(!re.isPresent())
            {
                return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
            }
            else {
                Remarks r = re.get();
                RemarkResponseDto responseDto = new RemarkResponseDto();
                responseDto.setRemarkId(r.getRemarkId());
                responseDto.setRemarkName(r.getRemarkName());
                responseDto.setCreatedDate(r.getCreatedDate());
                responseDto.setCreatedBy(r.getCreatedBy());
                responseDto.setModifiedDate(r.getModifiedDate());
                responseDto.setModifiedBy(r.getModifiedBy());
                return ResponseEntity.ok(responseDto);
            }}
    }
    public void deleteRemark(String remarkId){
        remarkService.deleteRemark(remarkId);

    }

}


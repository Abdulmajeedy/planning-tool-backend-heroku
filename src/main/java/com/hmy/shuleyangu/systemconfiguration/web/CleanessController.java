package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.CleanessRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.CleanessResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.models.Cleaness;
import com.hmy.shuleyangu.systemconfiguration.repository.CleanessRepository;
import com.hmy.shuleyangu.systemconfiguration.service.CleanessService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.CleanessApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CleanessController implements CleanessApi {
    @Autowired
    private CleanessService cleanessService;
    @Autowired
    private CleanessRepository cleanessRepository;

    public CleanessController(CleanessService cleanessService){
        this.cleanessService = cleanessService;
    }
    public ResponseEntity<List<CleanessResponseDto>> getCleaness(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Cleaness> cln = cleanessService.findAllCleaness(pageRequest);
        List<CleanessResponseDto> cleanessResponseDtos = new ArrayList<>();
        for(Cleaness c:cln)
        {
            CleanessResponseDto responseDto = new CleanessResponseDto();
            responseDto.setRemark(c.getRemark());
            responseDto.setRate(c.getRate());
            responseDto.setCreatedDate(c.getCreatedDate());
            responseDto.setCreatedBy(c.getCreatedBy());
            responseDto.setModifiedDate(c.getModifiedDate());
            responseDto.setModifiedBy(c.getModifiedBy());
            cleanessResponseDtos.add(responseDto);
        }
        return ResponseEntity.ok(cleanessResponseDtos);
    }

    @Override
    public ResponseEntity<CleanessResponseDto> registerNewCleanness(CleanessRequestDto crd) {
        return cleanessService.addNewCleaness(crd);
    }

    public ResponseEntity<CleanessResponseDto> getCleannessById(String cleannessId) {
        {
            Optional<Cleaness> cl = cleanessService.getCleanessById(cleannessId);
            if(!cl.isPresent())
            {
                return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
            }
            else {
                Cleaness c = cl.get();
                CleanessResponseDto responseDto = new CleanessResponseDto();
                responseDto.setCleanessId(c.getCleanessId());
                responseDto.setRemark(c.getRemark());
                responseDto.setRate(c.getRate());
                responseDto.setCreatedDate(c.getCreatedDate());
                responseDto.setCreatedBy(c.getCreatedBy());
                responseDto.setModifiedDate(c.getModifiedDate());
                responseDto.setModifiedBy(c.getModifiedBy());

                return ResponseEntity.ok(responseDto);
            }}
    }

}

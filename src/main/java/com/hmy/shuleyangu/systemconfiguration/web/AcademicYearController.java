package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.AcademicYearRepository;
import com.hmy.shuleyangu.systemconfiguration.service.AcademicYearService;
import com.hmy.shuleyangu.systemconfiguration.web.api.AcademicYearApi;
import com.hmy.shuleyangu.systemconfiguration.web.api.ZoneApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AcademicYearController implements AcademicYearApi {
    private AcademicYearService academicYearService;
    private AcademicYearRepository academicYearRepository;
    private  ModelMapper modelMapper;

    @Autowired
    public AcademicYearController(AcademicYearService academicYearService, ModelMapper modelMapper){

        this.academicYearService=academicYearService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<AcademicYearResponseDto>> getAcademicYears(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<AcademicYear> academicYears = academicYearService.findAllAcademicYears(pageRequest);
        List<AcademicYearResponseDto> academicDto = new ArrayList<>();
        for(AcademicYear a:academicYears)
        {
            AcademicYearResponseDto responseDto = new AcademicYearResponseDto();
            responseDto.setAcademicYearId(a.getAcademicYearId());
            responseDto.setAcademicYearName(a.getAcademicYearName());
            responseDto.setStatus(a.getStatus());
            responseDto.setCreatedDate(a.getCreatedDate());
            responseDto.setCreatedBy(a.getCreatedBy());
            responseDto.setModifiedDate(a.getModifiedDate());
            responseDto.setModifiedBy(a.getModifiedBy());
            academicDto.add(responseDto);
        }
        return ResponseEntity.ok(academicDto);
    }

    public void registerNewAcademicYear(AcademicYearRequestDto academicYear){
        academicYearService.addNewAcademicYear(academicYear);
    }

    @Override
    public ResponseEntity<AcademicYearResponseDto> getAcademicYearById(String zoneId) {
        return null;
    }

    public void deleteById(String academicYearId){
        academicYearService.deleteAcademicYear(academicYearId);

    }

    public ResponseEntity updateAcademicYear(String academicYearId, AcademicYearResponseDto yearToUpdate){
        Optional<AcademicYear> ay = academicYearService.getAcademicYearById(academicYearId);
        academicYearService.updateAcademicYear(academicYearId,yearToUpdate);
        AcademicYear a = ay.get();
        AcademicYearResponseDto responseDto = new AcademicYearResponseDto();
        responseDto.setAcademicYearId((academicYearId));
        responseDto.setAcademicYearName(a.getAcademicYearName());
        responseDto.setModifiedDate(a.getModifiedDate());
        responseDto.setModifiedBy(a.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}

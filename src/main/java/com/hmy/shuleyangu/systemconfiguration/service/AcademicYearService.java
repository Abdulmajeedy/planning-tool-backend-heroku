package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.AcademicYearRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicYearService {
    @Autowired
    private AcademicYearRepository academicYearRepository;


    @Autowired
    public AcademicYearService(AcademicYearRepository academicYearRepository){
        this.academicYearRepository = academicYearRepository;

    }

    public List<AcademicYear> findAllAcademicYears(PageRequest pageRequest) {
        return academicYearRepository.findAll(pageRequest).getContent();
    }

    public ResponseEntity<AcademicYearResponseDto> addNewAcademicYear(AcademicYearRequestDto ay) {
        AcademicYear a = new AcademicYear();
        a.setAcademicYearName(ay.getAcademicYearName());
        a.setStatus(ay.getStatus());
        academicYearRepository.save(a);

        AcademicYearResponseDto responseDto = new AcademicYearResponseDto();
        responseDto.setAcademicYearId(a.getAcademicYearId());
        responseDto.setAcademicYearName(a.getAcademicYearName());
        responseDto.setStatus(a.getStatus());
        responseDto.setCreatedDate(a.getCreatedDate());
        responseDto.setCreatedBy(a.getCreatedBy());
        responseDto.setModifiedDate(a.getModifiedDate());
        responseDto.setModifiedBy(a.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }


    public void deleteAcademicYear(String academicYearId){

        academicYearRepository.deleteById(academicYearId);
    }

    public Optional<AcademicYear> getAcademicYearById(String academicYearId){
        return academicYearRepository.findById(academicYearId);
    }

    public void updateAcademicYear(String academicYearId, AcademicYear ay) {
        academicYearRepository.findById(academicYearId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Academic year with Id "+ academicYearId + " does not exist"
                ));

        ay.setAcademicYearId(academicYearId);
//        AcademicYear a = new AcademicYear();
//        a.setModifiedBy(ay.getModifiedBy());
//        a.setAcademicYearName(ay.getAcademicYearName());
        academicYearRepository.save(ay);

    }

}

package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.AcademicYearResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.repository.AcademicYearRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicYearService {
    private final AcademicYearRepository academicYearRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AcademicYearService(AcademicYearRepository academicYearRepository, ModelMapper modelMapper){
        this.academicYearRepository = academicYearRepository;
        this.modelMapper = modelMapper;
    }

    public List<AcademicYear> findAllAcademicYears(PageRequest pageRequest) {
        return academicYearRepository.findAll(pageRequest).getContent();
    }
    public void addNewAcademicYear(AcademicYearRequestDto ay) {
        AcademicYear a = new AcademicYear();
        a.setAcademicYearName(ay.getAcademicYearName());
        a.setStatus(ay.getStatus());
        academicYearRepository.save(a);
    }
    public void deleteAcademicYear(String academicYearId){
        academicYearRepository.deleteById(academicYearId);
    }
    public Optional<AcademicYear> getAcademicYearById(String academicYearId){
        return academicYearRepository.findById(academicYearId);
    }
    public void updateAcademicYear(String academicYearId, AcademicYearResponseDto ay) {
        academicYearRepository.findById(academicYearId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Academic year with Id "+ academicYearId + " does not exist"
                ));
        ay.setAcademicYearId(academicYearId);
        AcademicYear a = new AcademicYear();
        a.setModifiedBy(ay.getModifiedBy());
        a.setAcademicYearName(ay.getAcademicYearName());
        academicYearRepository.save(a);

    }

}

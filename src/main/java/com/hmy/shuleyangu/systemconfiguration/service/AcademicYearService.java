package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.repository.AcademicYearRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<AcademicYear> getAcademicYears() {
        return (List<AcademicYear>) academicYearRepository.findAll();
    }

    public void addNewAcademicYear(AcademicYear academicYear){
        academicYearRepository.save(academicYear);

    }
    public Optional<AcademicYear> getAcademicYearById(String academicYearId){

        return academicYearRepository.findById(academicYearId);
    }

}

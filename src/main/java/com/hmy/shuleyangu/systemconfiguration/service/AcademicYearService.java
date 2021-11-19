package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.AcademicYearRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcademicYearService {
    private final AcademicYearRepository academicYearRepository;

    @Autowired
    public AcademicYearService(AcademicYearRepository academicYearRepository){
        this.academicYearRepository = academicYearRepository;
    }

    public List<AcademicYear> getAcademicYears() {
        return (List<AcademicYear>) academicYearRepository.findAll();
    }

    public void addNewAcademicYear(AcademicYear academicYear){
        academicYearRepository.save(academicYear);

    }
    public Optional<AcademicYear> getAcademicYearById(UUID academicYearId){

        return academicYearRepository.findById(academicYearId);
    }

}

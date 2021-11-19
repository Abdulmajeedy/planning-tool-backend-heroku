package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.models.AcademicYear;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.AcademicYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AcademicYearController {
    private final AcademicYearService academicYearService;

    @Autowired
    public AcademicYearController(AcademicYearService academicYearService){

        this.academicYearService=academicYearService;
    }
    @GetMapping(path = "/getAcademicYears")
    public List<AcademicYear> getAcademicYears() {
        return academicYearService.getAcademicYears();

    }
    @PostMapping(path = "/addAcademicYear")
    public ResponseEntity<String> registerNewAcademicYear(@RequestBody AcademicYear academicYear)
    {
        academicYearService.addNewAcademicYear(academicYear);
        return ResponseEntity.ok("Academic year Added succesfully");
    }
}

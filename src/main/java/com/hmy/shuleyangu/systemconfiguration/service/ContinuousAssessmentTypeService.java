package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.ContinuousAssessmentType;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ContinuousAssessmentTypeRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinuousAssessmentTypeService {
    private final ContinuousAssessmentTypeRepository continuousAssessmentTypeRepository;

    @Autowired
    public ContinuousAssessmentTypeService(ContinuousAssessmentTypeRepository continuousAssessmentTypeRepository){
        this.continuousAssessmentTypeRepository = continuousAssessmentTypeRepository;
    }

    public List<ContinuousAssessmentType> getContinuousAssTypes() {
        return (List<ContinuousAssessmentType>) continuousAssessmentTypeRepository.findAll();
    }

}

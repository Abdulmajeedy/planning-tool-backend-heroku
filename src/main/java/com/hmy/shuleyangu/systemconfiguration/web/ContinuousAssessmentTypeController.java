package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.models.ContinuousAssessmentType;
import com.hmy.shuleyangu.systemconfiguration.service.ContinuousAssessmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ContinuousAssessmentTypeController {
    private final ContinuousAssessmentTypeService continuousAssessmentTypeService;

    @Autowired
    public ContinuousAssessmentTypeController(ContinuousAssessmentTypeService continuousAssessmentTypeService){

        this.continuousAssessmentTypeService=continuousAssessmentTypeService;
    }
    @GetMapping(path = "/getContinuousAssTypes")
    public List<ContinuousAssessmentType> getContinuousAssTypes() {
        return continuousAssessmentTypeService.getContinuousAssTypes();

    }
}

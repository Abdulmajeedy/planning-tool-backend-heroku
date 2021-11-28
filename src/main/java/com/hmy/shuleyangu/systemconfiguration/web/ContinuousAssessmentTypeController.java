//package com.hmy.shuleyangu.systemconfiguration.web;
//
//import com.hmy.shuleyangu.systemconfiguration.models.ContAssessmentType;
//import com.hmy.shuleyangu.systemconfiguration.service.ContAssessmentTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping
//public class ContinuousAssessmentTypeController {
//    private final ContAssessmentTypeService continuousAssessmentTypeService;
//
//    @Autowired
//    public ContinuousAssessmentTypeController(ContAssessmentTypeService continuousAssessmentTypeService){
//
//        this.continuousAssessmentTypeService=continuousAssessmentTypeService;
//    }
//    @GetMapping(path = "/getContinuousAssTypes")
//    public List<ContAssessmentType> getContinuousAssTypes() {
//        return continuousAssessmentTypeService.getContinuousAssTypes();
//
//    }
//}

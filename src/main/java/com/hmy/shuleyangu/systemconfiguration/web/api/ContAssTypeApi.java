package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessReqDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ContAssessRespDto;

import com.hmy.shuleyangu.systemconfiguration.models.ContAssessmentType;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RequestMapping("/contAssType")
public interface ContAssTypeApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<ContAssessRespDto>> getContAssessment(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ContAssessRespDto registerContAssType(@RequestBody ContAssessReqDto contAssReq) throws URISyntaxException;

    @RequestMapping(value = "/{contAssTypeId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<ContAssessRespDto> getContAssessTypeById(@PathVariable String contAssTypeId);

    @RequestMapping(value = "/{contAssTypeId}", method = RequestMethod.PUT,produces = "application/json",consumes="application/json")
    public ResponseEntity updateContAssType(@PathVariable("contAssTypeId")String contAssTypeId, @RequestBody ContAssessmentType cat);

}

package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.Extracariculum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RequestMapping("/extracurricular")
public interface ExtracaricullumApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<ExtracariculumResponseDto>> getExtraCariculums(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public  ResponseEntity<ExtracariculumResponseDto> registerNewExtracurricular(@RequestBody ExtracariculumRequestDto erd);

}

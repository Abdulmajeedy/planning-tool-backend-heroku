package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.DisplineRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.DisplineResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.LatenessRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.LatenessResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Lateness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/lateness")
public interface LatenessApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<LatenessResponseDto>> getLateness(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);
    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<LatenessResponseDto> registerNewLateness(@RequestBody LatenessRequestDto late) ;

}

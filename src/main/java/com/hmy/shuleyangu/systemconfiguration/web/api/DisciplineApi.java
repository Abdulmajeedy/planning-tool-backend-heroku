package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.DisplineRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.DisplineResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RequestMapping("/discipline")
public interface DisciplineApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<DisplineResponseDto>> getDiscipline(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);
    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
   public ResponseEntity<DisplineResponseDto> registerNewDiscipline(@RequestBody DisplineRequestDto dis) throws URISyntaxException;

}

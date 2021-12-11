package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.CombinationRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.CombinationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/subjectCombination")
public interface CombinationApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<CombinationResponseDto>> getCombinations(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @PostMapping(path = "/")
    public ResponseEntity<CombinationResponseDto> registerNewCombination(@RequestBody CombinationRequestDto combinationRequestDto);

}

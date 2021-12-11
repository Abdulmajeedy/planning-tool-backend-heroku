package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ClassRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ClassResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("classes")
public interface ClassApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<ClassResponseDto>> getClasses(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);


    @PostMapping(path = "/")
    public ResponseEntity<ClassResponseDto> registerClass(@RequestBody ClassRequestDto classRequestDto);

}

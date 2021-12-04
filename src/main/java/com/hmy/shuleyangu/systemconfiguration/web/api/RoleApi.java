package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.RoleRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RoleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("/roles")
public interface RoleApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<RoleResponseDto>> getRoles(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);
    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<RoleResponseDto> registerNewRole(@RequestBody RoleRequestDto rrd);


}

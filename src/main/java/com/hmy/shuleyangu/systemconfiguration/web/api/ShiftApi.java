package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ShiftRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShiftResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/shifts")
public interface ShiftApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<ShiftResponseDto>> getShifts(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<ShiftResponseDto> registerNewShift(@RequestBody ShiftRequestDto shiftRequestDto);

    @RequestMapping(value = "/{shiftId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<ShiftResponseDto> getShiftById(@PathVariable String shiftId);

    @DeleteMapping(path = "/{shiftId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Shift Deleted")
    public void deleteById(@PathVariable("shiftId")String specializationId);


}



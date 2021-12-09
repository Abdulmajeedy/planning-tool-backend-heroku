package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.RemarkRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RemarkResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/remarks")
public interface RemarkApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<RemarkResponseDto>> getRemarks(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<RemarkResponseDto> registerNewRemark(@RequestBody RemarkRequestDto remarkRequestDto);

    @RequestMapping(value = "/{remarkId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<RemarkResponseDto> getRemarkById(@PathVariable String remarkId);

    @DeleteMapping(path = "/{remarkId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Remark Deleted")
    public void deleteRemark(@PathVariable("remarkId")String remarkId);


}




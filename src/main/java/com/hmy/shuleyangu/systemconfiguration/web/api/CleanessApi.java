package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.Cleaness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/cleanness")
@CrossOrigin
public interface CleanessApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<CleanessResponseDto>> getCleaness(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<CleanessResponseDto> registerNewCleanness(@RequestBody CleanessRequestDto crd) ;

    @RequestMapping(value = "/{cleannessId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<CleanessResponseDto> getCleannessById(@PathVariable String cleannessId);

}

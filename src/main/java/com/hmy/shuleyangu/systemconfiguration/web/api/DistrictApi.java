package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/district")
public interface DistrictApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<DistrictResponseDto>> getDistricts(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @PostMapping(path = "/")
   public ResponseEntity<DistrictResponseDto> registerNewDistrict(@RequestBody DistrictRequestDto districtRequestDto);

  @GetMapping(path = "/{districtId}")
    public ResponseEntity<DistrictResponseDto> getDistrictById(@PathVariable UUID districtId);

    @DeleteMapping(path = "/{districtId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "District has been deleted")
    public void deleteById(@PathVariable("districtId")UUID districtId);

    @PutMapping(path = "/{districtId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "District has been updated")
    public void updateDistrict(@PathVariable("districtId")UUID districtId,
                           @RequestBody District districtToUpdate);

}




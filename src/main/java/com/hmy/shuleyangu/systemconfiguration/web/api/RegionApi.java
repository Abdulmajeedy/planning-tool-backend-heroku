package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/region")
public interface RegionApi {

    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<RegionResponseDto>> getRegions(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);


    @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<RegionResponseDto> registerNewRegion(@RequestBody RegionRequestDto regions);

    @RequestMapping(value = "/{regionId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<RegionResponseDto> getRegionById(@PathVariable String regionId);

    @DeleteMapping(path = "/{regionId}")
    public void deleteById(@PathVariable("regionId")String regionId);

    @PutMapping(path = "/{regionId}")
    public void updateRegion(@PathVariable("regionId ")String regionId,
                           @RequestBody Region regionToUpdate);
}



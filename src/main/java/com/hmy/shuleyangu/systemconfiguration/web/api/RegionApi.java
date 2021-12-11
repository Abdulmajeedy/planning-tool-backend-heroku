package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/region")
public interface RegionApi {

    @GetMapping(path = "/")
    public ResponseEntity<List<RegionResponseDto>> getRegions(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);


    @PostMapping(path = "/")
    public ResponseEntity<RegionResponseDto> registerRegion(@RequestBody RegionRequestDto regions);

   @GetMapping(path = "/{regionId}")
    public ResponseEntity<RegionResponseDto> getRegionById(@PathVariable String regionId);

    @DeleteMapping(path = "/{regionId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Region has been Deleted")
    public void deleteById(@PathVariable("regionId")String regionId);

    @PutMapping(path = "/{regionId}")
    public ResponseEntity updateRegion(@PathVariable("regionId ")String regionId,
                           @RequestBody Region regionToUpdate);
}



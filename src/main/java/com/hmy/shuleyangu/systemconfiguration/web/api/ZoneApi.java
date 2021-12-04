package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/zone")
public interface ZoneApi {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<ZoneResponseDto>> getZones(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

   @RequestMapping(value = "/", method = RequestMethod.POST,produces = "application/json",consumes="application/json")
    public ResponseEntity<ZoneResponseDto> registerNewZone(@RequestBody ZoneRequestDto zones);

    @RequestMapping(value = "/{zoneId}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<ZoneResponseDto> getZoneById(@PathVariable String zoneId);

    @DeleteMapping(path = "/{zoneId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Zone Deleted")
    public void deleteById(@PathVariable("zoneId")String zoneId);

    @RequestMapping(value = "/{zoneId}", method = RequestMethod.PUT,produces = "application/json",consumes="application/json")
    public ResponseEntity updateZone(@PathVariable("zoneId")String zoneId, @RequestBody Zones zoneToUpdate);

}



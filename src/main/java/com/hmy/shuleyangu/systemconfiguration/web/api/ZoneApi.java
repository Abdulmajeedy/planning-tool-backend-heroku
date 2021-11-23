package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<ZoneResponseDto> getZoneById(@PathVariable UUID zoneId);

    @DeleteMapping(path = "/{zoneId}")
    public void deleteById(@PathVariable("zoneId")UUID zoneId);

    @PutMapping(path = "/{zoneId}")
    public void updateZone(@PathVariable("zoneId")UUID zoneId,
                           @RequestBody Zones zoneToUpdate);
//    @RequestMapping(value = { "/{zoneId}"}, method = RequestMethod.PUT,produces = "application/json",consumes="application/json")
//    public ResponseEntity<ZoneResponseDto> updateZone(@PathVariable  UUID zoneId, @RequestBody ZoneRequestDto categoryRequestDto);

}



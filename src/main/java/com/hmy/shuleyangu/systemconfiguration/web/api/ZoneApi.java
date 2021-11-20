package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("/zone")
public interface ZoneApi {

    @GetMapping(path = "/")
    public List<Zones> getZones();

    @PostMapping(path = "/")
    public ResponseEntity<String> registerNewZone(@RequestBody ZoneRequestDto zones);

    @GetMapping(path = "/{zone_id}")
    public ZoneRequestDto getZoneById(@PathVariable("zone_id") UUID zoneId);

    @DeleteMapping(path = "/{zone_id}")
    public void deleteById(@PathVariable("zone_id")UUID zoneId);

    @PutMapping(path = "/{zone_id}")
    public void updateZone(@PathVariable("id")UUID zoneId,
                           @RequestBody Zones zoneToUpdate);
}

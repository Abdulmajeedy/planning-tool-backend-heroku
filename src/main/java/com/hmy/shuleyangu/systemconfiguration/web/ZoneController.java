package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import com.hmy.shuleyangu.systemconfiguration.web.api.ZoneApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ZoneController implements ZoneApi {
    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService){

        this.zoneService=zoneService;
    }

    public List<Zones> getZones() {
        return zoneService.getZones();
    }
    public ResponseEntity<String>registerNewZone(@RequestBody ZoneRequestDto zones)
    {
        zoneService.addNewZone(zones);
        return ResponseEntity.ok("ZoneAdded succesfully");
    }

    public ZoneRequestDto getZoneById(@PathVariable("id") UUID zoneId){

        return zoneService.getZoneById(zoneId);
    }

    public void deleteById(@PathVariable("id")UUID zoneId){
        zoneService.deleteZone(zoneId);
    }

    @PutMapping(path = "updateZone/{id}")
    public void updateZone(@PathVariable("id")UUID zoneId,
                           @RequestBody Zones zoneToUpdate){
        zoneService.updateZone(zoneId,zoneToUpdate);
    }

}

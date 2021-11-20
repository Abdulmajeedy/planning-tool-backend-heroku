package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class ZoneController {
    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService){

        this.zoneService=zoneService;
    }
    @GetMapping(path = "/getZones")
    public List<Zones> getZones() {
        return zoneService.getZones();

    }
    @PostMapping(path = "/addZone")
    public ResponseEntity<String>registerNewZone(@RequestBody Zones zones)
    {
        zoneService.addNewZone(zones);
        return ResponseEntity.ok("ZoneAdded succesfully");
    }
    @GetMapping(path = "/getZone/{id}")
    public ZoneDto getZoneById(@PathVariable("id") UUID zoneId){

        return zoneService.getZoneById(zoneId);
    }


    @DeleteMapping(path = "deleteZone/{id}")
    public void deleteById(@PathVariable("id")UUID zoneId){
        zoneService.deleteZone(zoneId);
    }
//
    @PutMapping(path = "updateZone/{id}")
    public void updateZone(@PathVariable("id")UUID zoneId,
                           @RequestBody Zones zoneToUpdate){
        zoneService.updateZone(zoneId,zoneToUpdate);

    }

}

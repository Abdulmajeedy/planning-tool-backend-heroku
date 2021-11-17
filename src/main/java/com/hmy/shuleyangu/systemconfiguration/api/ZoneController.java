package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String registerNewZone(@RequestBody Zones zones)
    {
//        zoneService.addNewZone(zones);
        if(zones != null) {
            zoneService.addNewZone(zones);
            return "Added a zone successfully";
        } else {
            return "Request does not contain a body";
        }


    }
    @GetMapping(path = "/getZone/{id}")
    public Zones getZoneById(@PathVariable("id") UUID zoneId){

        return zoneService.getZoneById(zoneId).orElse(null);
    }


    @DeleteMapping(path = "deleteZone/{id}")
    public void deleteById(@PathVariable("id")UUID zoneId){
        zoneService.deleteZone(zoneId);
    }
//
//    @PutMapping(path = "updateZone/{id}")
//    public void updateZone(@PathVariable("id")UUID zoneId,
//                           @RequestBody Zones zoneToUpdate){
//        zoneService.updateZoneById(zoneId,zoneToUpdate);
//
//    }

}

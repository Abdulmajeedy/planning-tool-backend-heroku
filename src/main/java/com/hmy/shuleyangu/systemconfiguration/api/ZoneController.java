package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "zones")
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
    public void registerNewStudent(@RequestBody Zones zones)
    {
        zoneService.addNewZone(zones);

    }


}

package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class RegionController {
    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService){

        this.regionService=regionService;
    }
    @GetMapping(path = "/getRegions")
    public List<Region> getRegions() {
        return regionService.getRegions();

    }
    @PostMapping(path = "/addRegion")
    public void registerNewRegion(@RequestBody Region region)
    {
        regionService.addNewRegion(region);
    }
    @GetMapping(path = "/getRegion/{id}")
    public Region getRegionById(@PathVariable("id") UUID regionId){

        return regionService.getRegionById(regionId).orElse(null);
    }
    @DeleteMapping(path = "deleteRegion/{id}")
    public void deleteById(@PathVariable("id")UUID regionId){

        regionService.deleteRegion(regionId);
    }
//

}

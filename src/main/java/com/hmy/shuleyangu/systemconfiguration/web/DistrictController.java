package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class DistrictController {
    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService){

        this.districtService=districtService;
    }
    @GetMapping(path = "/getDistricts")
    public List<District> getDistricts() {
        return districtService.getDistricts();

    }
    @PostMapping(path = "/addDistrict")
    public String registerNewDistrict(@RequestBody District district)
    {
        if(district != null) {
            districtService.addNewDistrict(district);
            return "Added a district successfully";
        } else {
            return "Request does not contain a body";
        }
    }
    @GetMapping(path = "/getDistrict/{id}")
    public District getDistrict(@PathVariable("id") UUID districtId){

        return districtService.getDistrictById(districtId).orElse(null);
    }


    @DeleteMapping(path = "deleteDistrict/{id}")
    public void deleteById(@PathVariable("id")UUID zoneId){
        districtService.deleteDistrict(zoneId);
    }


    @PutMapping(path = "updateDistrict/{id}")
    public void updateZone(@PathVariable("id")UUID districtId,
                           @RequestBody District districtToUpdate){
        districtService.updateDistrict(districtId,districtToUpdate);

    }
}

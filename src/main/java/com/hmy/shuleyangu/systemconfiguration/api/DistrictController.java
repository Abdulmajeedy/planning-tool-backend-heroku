package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

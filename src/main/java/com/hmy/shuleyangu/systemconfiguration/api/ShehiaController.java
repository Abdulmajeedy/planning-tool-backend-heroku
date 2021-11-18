package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.ShehiaService;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ShehiaController {
    private final ShehiaService shehiaService;

    @Autowired
    public ShehiaController(ShehiaService shehiaService){

        this.shehiaService=shehiaService;
    }
    @GetMapping(path = "/getShehia")
    public List<Shehia> getShehia() {
        return shehiaService.getShehia();

    }
}

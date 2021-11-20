package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.service.ShehiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @PostMapping(path = "/addShehia")
    public String registerNewShehia(@RequestBody Shehia shehia)
    {
        if(shehia != null) {
            shehiaService.addNewShehia(shehia);
            return "Added a shehia successfully";
        } else {
            return "Request does not contain a body";
        }
    }
    @GetMapping(path = "/getShehia/{id}")
    public Shehia getDistrict(@PathVariable("id") UUID shehiaId){

        return shehiaService.getShehiaById(shehiaId).orElse(null);
    }


    @DeleteMapping(path = "deleteShehia/{id}")
    public void deleteById(@PathVariable("id")UUID shehiaId){
        shehiaService.deleteShehia(shehiaId);
    }


    @PutMapping(path = "updateShehia/{id}")
    public void updateSheia(@PathVariable("id")UUID shehiaId,
                           @RequestBody Shehia shehiaToUpdate){
        shehiaService.updateShehia(shehiaId,shehiaToUpdate);

    }
}

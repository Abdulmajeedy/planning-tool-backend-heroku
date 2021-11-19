package com.hmy.shuleyangu.systemconfiguration.api;

import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.service.TermTypeService;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TermTypeController {
    private final TermTypeService termTypeService;

    @Autowired
    public TermTypeController(TermTypeService termTypeService){

        this.termTypeService=termTypeService;
    }
    @GetMapping(path = "/getTermTypes")
    public List<TermTypes> getTermTypes() {
        return termTypeService.getTermTypes();

    }
    @PostMapping(path = "/addTermType")
    public ResponseEntity<String> registerNewTermType(@RequestBody TermTypes termTypes)
    {
        termTypeService.addNewTermType(termTypes);
        return ResponseEntity.ok("Term type system Added succesfully");
    }
}

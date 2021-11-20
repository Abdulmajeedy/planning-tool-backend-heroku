package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import com.hmy.shuleyangu.systemconfiguration.service.TermTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.ok("Term type system Added successfully");
    }
    @GetMapping(path = "/getTermType/{id}")
    public TermTypes getTermType(@PathVariable("id") UUID termTypeId){

        return termTypeService.getTermTypeById(termTypeId).orElse(null);
    }
}

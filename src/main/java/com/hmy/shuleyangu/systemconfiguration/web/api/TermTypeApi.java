package com.hmy.shuleyangu.systemconfiguration.web.api;


import com.hmy.shuleyangu.systemconfiguration.dto.TermTypeRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.TermTypeResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/termTypes")
public interface TermTypeApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<TermTypeResponseDto>> getTermTypes(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

    @PostMapping(path = "/")
     public ResponseEntity<TermTypeResponseDto> registerNewTermType(@RequestBody TermTypeRequestDto termTypeRequestDto);

    @GetMapping(path = "/{termTypeId}")
    public ResponseEntity<TermTypeResponseDto> getTermTypeById(@PathVariable String termTypeId);

    @DeleteMapping(path = "/{termTypeId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Term type has been Deleted")
    public void deleteById(@PathVariable("termTypeId")String termTypeId);

    @PutMapping(path = "/{termTypeId}")
    public ResponseEntity updateTermType(@PathVariable("termTypeId ")String termTypeId,
                                       @RequestBody TermTypes termToUpdate);

}

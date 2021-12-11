package com.hmy.shuleyangu.systemconfiguration.web.api;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/shehia")
public interface ShehiaApi {
    @GetMapping(path = "/")
    public ResponseEntity<List<ShehiaResponseDto>> getShehia(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

  @PostMapping(path = "/")
   public ResponseEntity<ShehiaResponseDto> registerNewShehia(@RequestBody ShehiaRequestDto shehiaDto);

    @GetMapping(path = "/{shehiaId}")
    public ResponseEntity<ShehiaResponseDto> getShehiaById(@PathVariable String shehiaId);

    @DeleteMapping(path = "/{shehiaId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Shehia has been Deleted")
    public void deleteById(@PathVariable("shehiaId")String shehiaId);

    @PutMapping(path = "/{shehiaId}")
    public ResponseEntity updateShehia(@PathVariable("shehiaId ")String shehiaId,
                                       @RequestBody Shehia shehiaToUpdate);
}

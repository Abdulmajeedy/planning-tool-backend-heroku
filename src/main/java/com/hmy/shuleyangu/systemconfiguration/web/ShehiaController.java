package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ShehiaRepository;
import com.hmy.shuleyangu.systemconfiguration.service.DistrictService;
import com.hmy.shuleyangu.systemconfiguration.service.RegionService;
import com.hmy.shuleyangu.systemconfiguration.service.ShehiaService;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.RegionApi;
import com.hmy.shuleyangu.systemconfiguration.web.api.ShehiaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShehiaController implements ShehiaApi {
    @Autowired
    private ShehiaService shehiaService;
    private ShehiaRepository shehiaRepository;
    private DistrictService districtService;

    public ShehiaController(DistrictService districtService,ShehiaService shehiaService){
        this.shehiaService = shehiaService;
        this.districtService = districtService;
    }


public ResponseEntity<List<ShehiaResponseDto>> getShehia(int page, int size)
{

    PageRequest pageRequest = PageRequest.of(page, size);
    List<Shehia> shehia = shehiaService.getShehia(pageRequest);

    List<ShehiaResponseDto> sheh = new ArrayList<>();
    for(Shehia s:shehia)
    {
        ShehiaResponseDto responseDto = new ShehiaResponseDto();
        responseDto.setShehiaId(s.getShehiaId());
        responseDto.setShehiaCode(s.getShehiaCode());
        responseDto.setShehiaName(s.getShehiaName());
        responseDto.setCreatedDate(s.getCreatedDate());
        responseDto.setCreatedBy(s.getCreatedBy());
        responseDto.setModifiedDate(s.getModifiedDate());
        responseDto.setModifiedBy(s.getModifiedBy());

        sheh.add(responseDto);
    }
    return ResponseEntity.ok(sheh);
}

    public ResponseEntity<ShehiaResponseDto> registerNewShehia(ShehiaRequestDto shehia){

        return shehiaService.addNewShehia(shehia);
    }


public ResponseEntity<ShehiaResponseDto> getShehiaById(String shehiaId)
{
    Optional<Shehia> sh = shehiaService.getShehiaById(shehiaId);
    if(!sh.isPresent())
    {
        return new ResponseEntity(ApiResponse.error("Invalid ID",null),HttpStatus.NOT_FOUND);
    }
    else
    {
        Shehia s = sh.get();
        ShehiaResponseDto responseDto = new ShehiaResponseDto();
        responseDto.setShehiaId(s.getShehiaId());
        responseDto.setShehiaCode(s.getShehiaCode());
        responseDto.setShehiaName(s.getShehiaName());
        responseDto.setCreatedDate(s.getCreatedDate());
        responseDto.setCreatedBy(s.getCreatedBy());
        responseDto.setModifiedDate(s.getModifiedDate());
        responseDto.setModifiedBy(s.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}


    public void deleteById(String shehiaId){
        shehiaService.deleteShehia(shehiaId);
    }

    @Override
    public ResponseEntity updateShehia(String shehiaId, Shehia shehiaToUpdate){
        Optional<Shehia> sh = shehiaService.getShehiaById(shehiaId);
        shehiaService.updateShehia(shehiaId,shehiaToUpdate);
        Shehia s = sh.get();
        ShehiaResponseDto responseDto = new ShehiaResponseDto();
        responseDto.setShehiaId(s.getShehiaId());
        responseDto.setShehiaCode(s.getShehiaCode());
        responseDto.setShehiaName(s.getShehiaName());
        responseDto.setCreatedDate(s.getCreatedDate());
        responseDto.setCreatedBy(s.getCreatedBy());
        responseDto.setModifiedDate(s.getModifiedDate());
        responseDto.setModifiedBy(s.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}


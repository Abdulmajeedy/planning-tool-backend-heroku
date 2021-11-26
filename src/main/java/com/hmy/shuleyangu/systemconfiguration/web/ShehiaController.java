package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaResponseDto;
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


    public ResponseEntity<ShehiaResponseDto> registerNewShehia(ShehiaRequestDto shehiaDto)
    {
        Optional<District> ds = districtService.getDistrictById(shehiaDto.getDistrictId());
        if(!ds.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Shehia s = new Shehia();
        s.setShehiaCode(shehiaDto.getShehiaCode());
        s.setShehiaName(shehiaDto.getShehiaName());
        s.setDistrict(ds.get());
        Shehia responsive = shehiaService.addNewShehia(s);

        ShehiaResponseDto responseDto = new ShehiaResponseDto();
        responseDto.setDistrictName((responsive.getDistrict().getDistrictName()));
        responseDto.setShehiaId(responsive.getShehiaId());
        responseDto.setShehiaCode(responsive.getShehiaCode());
        responseDto.setShehiaName(responsive.getShehiaName());
        responseDto.setCreatedBy(responsive.getCreatedBy());
        responseDto.setCreatedDate(responsive.getCreatedDate());
        responseDto.setModifiedBy(responsive.getModifiedBy());
        responseDto.setModifiedDate(responsive.getModifiedDate());
        responseDto.setShehiaId(s.getShehiaId());
        return ResponseEntity.ok(responseDto);

    }


public ResponseEntity<ShehiaResponseDto> getShehiaById(UUID shehiaId)
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


    public void deleteById(UUID shehiaId){
        shehiaService.deleteShehia(shehiaId);
    }

    @Override
    public ResponseEntity updateShehia(UUID shehiaId, Shehia shehiaToUpdate){
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


package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import com.hmy.shuleyangu.systemconfiguration.service.RegionService;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.RegionApi;
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
public class RegionController implements RegionApi {
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ZoneService zoneService;

    public RegionController(RegionService regionService,ZoneService zoneService){
        this.regionService=regionService;
        this.zoneService=zoneService;
    }

    public ResponseEntity<List<RegionResponseDto>> getRegions(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
         List<Region> regions = regionService.findAllRegions(pageRequest);

        List<RegionResponseDto> regn = new ArrayList<>();
        for(Region r:regions)
    {
        RegionResponseDto responseDto = new RegionResponseDto();
        responseDto.setRegionId(r.getRegionId());
        responseDto.setRegionCode(r.getRegionCode());
        responseDto.setRegionName(r.getRegionName());
        responseDto.setCreatedDate(r.getCreatedDate());
        responseDto.setCreatedBy(r.getCreatedBy());
        responseDto.setModifiedDate(r.getModifiedDate());
        responseDto.setModifiedBy(r.getModifiedBy());
        responseDto.setZoneId(r.getZones().getZoneId());
        regn.add(responseDto);
    }
    return ResponseEntity.ok(regn);

}

    @Override
    public ResponseEntity<RegionResponseDto> registerRegion(RegionRequestDto regions) {
        return regionService.addNewRegion(regions);
    }

    public ResponseEntity<RegionResponseDto> getRegionById(String regionId)
    {
        Optional<Region> rg = regionService.getRegionById(regionId);
        if(!rg.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null),HttpStatus.NOT_FOUND);
        }
        else
        {
            Region r = rg.get();
            RegionResponseDto responseDto = new RegionResponseDto();
            responseDto.setRegionId(r.getRegionId());
            responseDto.setRegionCode(r.getRegionCode());
            responseDto.setZoneId(r.getZones().getZoneId());
            responseDto.setRegionName(r.getRegionName());
            responseDto.setCreatedDate(r.getCreatedDate());
            responseDto.setCreatedBy(r.getCreatedBy());
            responseDto.setModifiedDate(r.getModifiedDate());
            responseDto.setModifiedBy(r.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    public void deleteById(String regionId){
        regionService.deleteRegion(regionId);
    }


    public ResponseEntity updateRegion(String regionId, Region regionToUpdate){
        Optional<Region> rg = regionService.getRegionById(regionId);
        regionService.updateRegion(regionId,regionToUpdate);
        Region r = rg.get();
        RegionResponseDto responseDto = new RegionResponseDto();
        responseDto.setRegionId(r.getRegionId());
        responseDto.setRegionCode(r.getRegionCode());
        responseDto.setRegionName(r.getRegionName());
        responseDto.setCreatedDate(r.getCreatedDate());
        responseDto.setCreatedBy(r.getCreatedBy());
        responseDto.setModifiedDate(r.getModifiedDate());
        responseDto.setModifiedBy(r.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}

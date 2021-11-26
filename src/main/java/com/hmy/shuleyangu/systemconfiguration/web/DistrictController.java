package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.*;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.repository.DistrictRepository;
import com.hmy.shuleyangu.systemconfiguration.service.DistrictService;
import com.hmy.shuleyangu.systemconfiguration.service.RegionService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.DistrictApi;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class DistrictController implements DistrictApi {
    private DistrictService districtService;
    private DistrictRepository districtRepository;
    private RegionService regionService;

    public DistrictController(DistrictService districtService,RegionService regionService){
        this.regionService=regionService;
        this.districtService=districtService;
    }
    public ResponseEntity<List<DistrictResponseDto>> getDistricts(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<District> districts = districtService.getDistricts(pageRequest);
        List<DistrictResponseDto> dis = new ArrayList<>();
        for(District d:districts)
        {
            DistrictResponseDto responseDto = new DistrictResponseDto();
            responseDto.setDistrictId(d.getDistrictId());
            responseDto.setDistrictCode(d.getDistrictCode());
            responseDto.setDistrictName(d.getDistrictName());
            responseDto.setCreatedDate(d.getCreatedDate());
            responseDto.setCreatedBy(d.getCreatedBy());
            responseDto.setModifiedDate(d.getModifiedDate());
            responseDto.setModifiedBy(d.getModifiedBy());
            dis.add(responseDto);
        }
        return ResponseEntity.ok(dis);
    }

    @Override
    public ResponseEntity<DistrictResponseDto> registerNewDistrict(DistrictRequestDto districtDto)
    {
        Optional<Region> rg = regionService.getRegionById(districtDto.getRegionId());

        if(!rg.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        District r = new District();
        r.setDistrictCode(districtDto.getDistrictCode());
        r.setDistrictName(districtDto.getDistrictName());
        r.setRegion(rg.get());
        District responsive = districtService.addNewDistrict(r);

        DistrictResponseDto responseDto = new DistrictResponseDto();
        responseDto.setRegionName((responsive.getRegion().getRegionName()));
        responseDto.setDistrictId(responsive.getDistrictId());
        responseDto.setDistrictCode(responsive.getDistrictCode());
        responseDto.setDistrictName(responsive.getDistrictName());
        responseDto.setCreatedBy(responsive.getCreatedBy());
        responseDto.setCreatedDate(responsive.getCreatedDate());
        responseDto.setModifiedBy(responsive.getModifiedBy());
        responseDto.setModifiedDate(responsive.getModifiedDate());
        responseDto.setRegionId(r.getDistrictId());
        return ResponseEntity.ok(responseDto);

    }
    @Override
    public ResponseEntity<DistrictResponseDto> getDistrictById(UUID districtId)
    {
        Optional<District> dis = districtService.getDistrictById(districtId);
        if(!dis.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null),HttpStatus.NOT_FOUND);
        }
        else
        {
            District d = dis.get();
            DistrictResponseDto responseDto = new DistrictResponseDto();
            responseDto.setDistrictId(d.getDistrictId());
            responseDto.setDistrictCode(d.getDistrictCode());
            responseDto.setDistrictName(d.getDistrictName());
            responseDto.getCreatedDate();
            responseDto.getCreatedBy();
            responseDto.setModifiedDate(d.getModifiedDate());
            responseDto.setModifiedBy(d.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    public void deleteById(UUID zoneId){
        districtService.deleteDistrict(zoneId);
    }

    public void updateDistrict(UUID districtId, District districtToUpdate){
        districtService.updateDistrict(districtId,districtToUpdate);

    }
}

package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ZoneService zoneService;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAllRegions(PageRequest pageRequest) {
        return regionRepository.findAll(pageRequest).getContent();
    }

    public Optional<Region> getRegionById(String regionId){

        return regionRepository.findById(regionId);
    }

    public ResponseEntity<RegionResponseDto> addNewRegion(RegionRequestDto regionDto) {
        Optional<Zones> zn = zoneService.getZoneById(regionDto.getZoneId());
        if(!zn.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Region r = new Region();
        Zones z = zn.get();

        r.setZones(z);
        r.setRegionCode(regionDto.getRegionCode());
        r.setRegionName(regionDto.getRegionName());
        regionRepository.save(r);

        RegionResponseDto responseDto = new RegionResponseDto();
        responseDto.setRegionId(r.getRegionId());
        responseDto.setRegionCode(r.getRegionCode());
        responseDto.setRegionName(r.getRegionName());
        responseDto.setCreatedDate(r.getCreatedDate());
        responseDto.setCreatedBy(r.getCreatedBy());
        responseDto.setModifiedDate(r.getModifiedDate());
        responseDto.setModifiedBy(r.getModifiedBy());
        responseDto.setZoneId(z.getZoneId());
        return  ResponseEntity.ok(responseDto);
    }


    public void deleteRegion(String regionId){
        regionRepository.deleteById(regionId);
    }

    public void updateRegion(String regionId, Region regionToUpdate) {
    }
}
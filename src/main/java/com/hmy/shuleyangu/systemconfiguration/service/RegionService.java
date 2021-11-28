package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegionService {
    private final RegionRepository regionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RegionService(RegionRepository regionRepository,ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.regionRepository = regionRepository;
    }

    public List<Region> findAllRegions(PageRequest pageRequest) {
        return regionRepository.findAll(pageRequest).getContent();
    }

    public Optional<Region> getRegionById(String regionId){

        return regionRepository.findById(regionId);
    }

    public void addNewRegion(RegionRequestDto regionDto) {
        Region r = new Region();
//        r.setRegionCode(region.getRegionCode());
//        r.setRegionName(region.getRegionName());
//        r.getZones().setZoneId(region.getZoneId());
//       System.out.println(region.getZoneId());
        Region region1 = modelMapper.map(regionDto,Region.class);
//      System.out.println(region1.getZones().getZoneId());
        regionRepository.save(region1);
    }


    public void deleteRegion(String regionId){
        regionRepository.deleteById(regionId);
    }


    public void updateRegion(String regionId, Region regionToUpdate) {
    }
}
package com.hmy.shuleyangu.systemconfiguration.service;

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

    public Region addNewRegion(Region region) {
      regionRepository.save(region);
        return region;
    }


    public void deleteRegion(String zoneId){
        regionRepository.deleteById(zoneId);
    }


    public void updateRegion(String regionId, Region regionToUpdate) {
    }
}
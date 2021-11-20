package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {

        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        return (List<Region>) regionRepository.findAll();

    }

    public void addNewRegion(Region region) {
      regionRepository.save(region);

    }

    public Optional<Region> getRegionById(UUID regionId) {

        return regionRepository.findById(regionId);
    }

    public void deleteRegion(UUID zoneId){
        regionRepository.deleteById(zoneId);
    }

}
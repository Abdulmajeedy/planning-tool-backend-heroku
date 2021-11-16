package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {
    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository){
        this.zoneRepository = zoneRepository;
    }

    public List<Zones> getZones() {
        return zoneRepository.findAll();

    }
    public void addNewZone(Zones zones) {
        Optional<Zones> zoneOptional = zoneRepository.findZoneByCode(zones.getZoneCode());
        if (zoneOptional.isPresent()){
            throw new IllegalStateException("zone already registered");
        }
        zoneRepository.save(zones);
    }

}

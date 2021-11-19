package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ZoneService {
    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository){
        this.zoneRepository = zoneRepository;
    }

    public List<Zones> getZones() {
        return (List<Zones>) zoneRepository.findAll();
    }

    public void addNewZone(Zones zones) {
      zoneRepository.save(zones);

    }

    public Optional<Zones>getZoneById(UUID zoneId){

        return zoneRepository.findById(zoneId);
    }

    public void deleteZone(UUID zoneId){
        zoneRepository.deleteById(zoneId);
    }

    public void updateZone(UUID zoneId, Zones zones) {
     zoneRepository.findById(zoneId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Zone with Id "+ zoneId + " does not exist"
                ));


        zones.setZoneID(zoneId);
        zoneRepository.save(zones);

    }


}

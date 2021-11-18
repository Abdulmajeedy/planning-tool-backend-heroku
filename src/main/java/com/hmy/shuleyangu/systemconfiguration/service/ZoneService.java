package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void updateZoneById(UUID zoneId, Zones zoneUpdate) {
        Optional<Zones> u = zoneRepository.findById(zoneId);

//        return zoneRepository.findById(zoneId).map(zone->{
//                    int indexOfZoneToUpdate = DB.indexOf(zone);
//                    if (indexOfZoneToUpdate>=0){
//                        DB.set(indexOfZoneToUpdate,new Zones(zoneId,zoneUpdate.getZoneCode(),zoneUpdate.getZoneName()));
//                        return 1;
//                    }
//                    return 0;
//
//                })
//                .orElse(0);

    }

}

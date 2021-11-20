package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;

@Service
public class ZoneService {
    private final ModelMapper modelMapper;
    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ModelMapper modelMapper, ZoneRepository zoneRepository){
        this.modelMapper = modelMapper;
        this.zoneRepository = zoneRepository;
    }

    public List<Zones> getZones() {
        return (List<Zones>) zoneRepository.findAll();
    }
//
//    public void addNewZone(Zones zones) {
//      zoneRepository.save(zones);
//
//    }
public Map<String,Boolean> addNewZone(Zones zoneDto){
    Zones zone = modelMapper.map(zoneDto,Zones.class);
    zoneRepository.save(zone);
    Map<String,Boolean> response= new HashMap<>();
    response.put("response",Boolean.TRUE);
    return response;
}

    public ZoneDto getZoneById(UUID zoneID) {
        return modelMapper.map(
                zoneRepository.findById(zoneID).orElseThrow(()-> new ResourceAccessException("Zone not found of this id::"+zoneID)),
                ZoneDto.class
        );
    }
//    public Optional<Zones> getZoneById(UUID zoneId){
//
//        return zoneRepository.findById(zoneId);
//    }

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

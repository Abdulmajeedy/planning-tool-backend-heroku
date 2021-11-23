package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ZoneService {
    private final ModelMapper modelMapper;
    @Autowired
    private ZoneRepository zoneRepository;
    public ZoneService(ModelMapper modelMapper, ZoneRepository zoneRepository){
        this.modelMapper = modelMapper;
        this.zoneRepository = zoneRepository;
    }

    public List<Zones> findAllZones(PageRequest pageRequest) {
        return zoneRepository.findAll(pageRequest).getContent();
    }

    public Zones addNewZone(Zones zones) {

        return zoneRepository.save(zones);
    }

    public Optional<Zones> getZoneById(String zoneId){

        return zoneRepository.findById(zoneId);
    }

    public void deleteZone(String zoneId){

        zoneRepository.deleteById(zoneId);
    }

    public void updateZone(String zoneId, Zones zones) {
     zoneRepository.findById(zoneId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Zone with Id "+ zoneId + " does not exist"
                ));


        zones.setId(zoneId);
        zoneRepository.save(zones);


    }




}

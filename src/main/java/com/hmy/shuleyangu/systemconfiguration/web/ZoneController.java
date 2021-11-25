package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.ZoneApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ZoneController implements ZoneApi {
    @Autowired
    private ZoneService zoneService;
    private ZoneRepository zoneRepository;

    public ZoneController(ZoneService zoneService){
        this.zoneService=zoneService;
    }

    public ResponseEntity<List<ZoneResponseDto>> getZones(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Zones> zones = zoneService.findAllZones(pageRequest);
        List<ZoneResponseDto> zon = new ArrayList<>();
        for(Zones z:zones)
        {
            ZoneResponseDto responseDto = new ZoneResponseDto();
            responseDto.setZoneId(z.getZoneId());
            responseDto.setZoneCode(z.getZoneCode());
            responseDto.setZoneName(z.getZoneName());
            responseDto.setCreatedDate(z.getCreatedDate());
            responseDto.setCreatedBy(z.getCreatedBy());
            responseDto.setModifiedDate(z.getModifiedDate());
            responseDto.setModifiedBy(z.getModifiedBy());
            zon.add(responseDto);
        }
        return ResponseEntity.ok(zon);
    }

    @Override
    public void registerNewZone(Zones zones){
        zoneService.addNewZone(zones);
    }

    public ResponseEntity<ZoneResponseDto> getZoneById(UUID zoneId)
    {
        Optional<Zones> zn = zoneService.getZoneById(zoneId);
        if(!zn.isPresent())
        {
            return new ResponseEntity(ApiResponse.error("Invalid ID",null),HttpStatus.NOT_FOUND);
        }
        else
        {
            Zones z = zn.get();
            ZoneResponseDto responseDto = new ZoneResponseDto();
            responseDto.setZoneId(z.getZoneId());
            responseDto.setZoneCode(z.getZoneCode());
            responseDto.setZoneName(z.getZoneName());
            responseDto.setCreatedDate(z.getCreatedDate());
            responseDto.setCreatedBy(z.getCreatedBy());
            responseDto.setModifiedDate(z.getModifiedDate());
            responseDto.setModifiedBy(z.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }


    public void deleteById(UUID zoneId){
        zoneService.deleteZone(zoneId);

    }

    public ResponseEntity updateZone(UUID zoneId, Zones zoneToUpdate){
        Optional<Zones> zn = zoneService.getZoneById(zoneId);
        zoneService.updateZone(zoneId,zoneToUpdate);
        Zones z = zn.get();
        ZoneResponseDto responseDto = new ZoneResponseDto();
        responseDto.setZoneId((zoneId));
        responseDto.setZoneCode(z.getZoneCode());
        responseDto.setZoneName(z.getZoneName());
        responseDto.setModifiedDate(z.getModifiedDate());
        responseDto.setModifiedBy(z.getModifiedBy());


        //return new ResponseEntity(ApiResponse.ok("Zone with Id "+ zoneId + " has been updated"),HttpStatus.OK);
    return ResponseEntity.ok(responseDto);
    }


}

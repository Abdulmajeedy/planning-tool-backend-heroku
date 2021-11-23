package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import com.hmy.shuleyangu.systemconfiguration.service.ZoneService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.ZoneApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            responseDto.setZoneId(z.getId());
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

    public ResponseEntity<ZoneResponseDto> registerNewZone(ZoneRequestDto zoneDto)
    {

        Zones z = new Zones();
        z.setZoneCode(zoneDto.getZoneCode());
        z.setZoneName(zoneDto.getZoneName());
        Zones responsive = zoneService.addNewZone(z);

        ZoneResponseDto responseDto = new ZoneResponseDto();
        responseDto.setZoneId(responsive.getId());
        responseDto.setZoneCode(responsive.getZoneCode());
        responseDto.setZoneName(responsive.getZoneName());
        responseDto.setCreatedBy(responsive.getCreatedBy());
        responseDto.setCreatedDate(responsive.getCreatedDate());
        responseDto.setModifiedBy(responsive.getModifiedBy());
        responseDto.setModifiedDate(responsive.getModifiedDate());
        responseDto.setZoneId(z.getId());
        return ResponseEntity.ok(responseDto);

    }
    @Override
    public ResponseEntity<ZoneResponseDto> getZoneById(String zoneId)
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
            responseDto.setZoneId(z.getId());
            responseDto.setZoneCode(z.getZoneCode());
            responseDto.setZoneName(z.getZoneName());
            responseDto.setCreatedDate(z.getCreatedDate());
            responseDto.setCreatedBy(z.getCreatedBy());
            responseDto.setModifiedDate(z.getModifiedDate());
            responseDto.setModifiedBy(z.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }


    public ResponseEntity<ZoneResponseDto>deleteById(String zoneId){
        zoneService.deleteZone(zoneId);
       return new ResponseEntity(ApiResponse.ok("Zone with Id "+ zoneId + " has been deleted"), null,HttpStatus.OK);

    }
    
    
    public void updateZone(String zoneId, Zones zoneToUpdate){
        zoneService.updateZone(zoneId,zoneToUpdate);
    }


}
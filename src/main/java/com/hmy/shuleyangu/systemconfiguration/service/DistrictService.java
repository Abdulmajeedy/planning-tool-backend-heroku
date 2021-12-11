package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.DistrictRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.DistrictResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.DistrictRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.RegionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private RegionService regionService;

        @Autowired
        public DistrictService(DistrictRepository districtRepository){
            this.districtRepository = districtRepository;

        }
    public List<District> findAllDistricts(PageRequest pageRequest) {
        return districtRepository.findAll(pageRequest).getContent();
    }

    public ResponseEntity<DistrictResponseDto> addNewDistrict(DistrictRequestDto district) {
            Optional<Region> rg = regionService.getRegionById(district.getRegionId());
        if(!rg.isPresent())
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    District d = new District();
        Region r = rg.get();
        d.setRegion(r);
        d.setDistrictCode(district.getDistrictCode());
        d.setDistrictName(district.getDistrictName());

        districtRepository.save(d);

        DistrictResponseDto responseDto = new DistrictResponseDto();
    responseDto.setDistrictId(d.getDistrictId());
    responseDto.setDistrictCode(d.getDistrictCode());
    responseDto.setDistrictName(d.getDistrictName());
    responseDto.setCreatedDate(d.getCreatedDate());
    responseDto.setCreatedBy(d.getCreatedBy());
    responseDto.setModifiedDate(d.getModifiedDate());
    responseDto.setModifiedBy(d.getModifiedBy());
    responseDto.setRegionId(r.getRegionId());
    return  ResponseEntity.ok(responseDto);


    }


    public Optional<District> getDistrictById(String districtId){

        return districtRepository.findById(districtId);
    }

    public void deleteDistrict(String districtId){
        districtRepository.deleteById(districtId);
    }

    public void updateDistrict(String districtId, District district) {
        districtRepository.findById(districtId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "District with Id " + districtId + " does not exist"
                ));

        district.setDistrictId(districtId);
        districtRepository.save(district);
    }
    }



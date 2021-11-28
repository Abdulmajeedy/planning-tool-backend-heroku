package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.DistrictRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RegionRequestDto;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.repository.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DistrictService {
        private final DistrictRepository districtRepository;
        private final ModelMapper modelMapper;

        @Autowired
        public DistrictService(DistrictRepository districtRepository, ModelMapper modelMapper){
            this.districtRepository = districtRepository;
            this.modelMapper = modelMapper;
        }
    public List<District> getDistricts(PageRequest pageRequest) {
        return districtRepository.findAll(pageRequest).getContent();
    }

    public void addNewDistrict(DistrictRequestDto district) {
    District d = new District();
    d.setDistrictCode(district.getDistrictCode());
    d.setDistrictName(district.getDistrictName());

        districtRepository.save(d);

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



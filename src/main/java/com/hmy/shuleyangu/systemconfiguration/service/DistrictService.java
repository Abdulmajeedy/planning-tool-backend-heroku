package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.DistrictRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DistrictService {
        private final DistrictRepository districtRepository;

        @Autowired
        public DistrictService(DistrictRepository districtRepository){
            this.districtRepository = districtRepository;
        }

        public List<District> getDistricts() {

            return (List<District>) districtRepository.findAll();
        }
    public void addNewDistrict(District district) {
        districtRepository.save(district);

    }

    public Optional<District> getDistrictById(UUID districtId){

        return districtRepository.findById(districtId);
    }

    public void deleteDistrict(UUID districtId){
        districtRepository.deleteById(districtId);
    }

    public void updateDistrict(UUID districtId, District district) {
        districtRepository.findById(districtId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "District with Id " + districtId + " does not exist"
                ));


        district.setDistrictId(districtId);
        districtRepository.save(district);
    }
    }



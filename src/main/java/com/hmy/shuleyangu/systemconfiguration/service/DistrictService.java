package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.DistrictRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    }

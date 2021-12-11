package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.DistrictResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShehiaResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.District;
import com.hmy.shuleyangu.systemconfiguration.models.Region;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.repository.ShehiaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShehiaService {
    @Autowired
    private ShehiaRepository shehiaRepository;
    @Autowired
    private DistrictService districtService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShehiaService(ShehiaRepository shehiaRepository, ModelMapper modelMapper){
        this.shehiaRepository = shehiaRepository;
        this.modelMapper = modelMapper;
    }

    public List<Shehia> getShehia(PageRequest pageRequest) {
        return shehiaRepository.findAll(pageRequest).getContent();
    }

    public ResponseEntity<ShehiaResponseDto> addNewShehia(ShehiaRequestDto sh) {
        Optional<District> ds = districtService.getDistrictById(sh.getDistrictId());
        if(!ds.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Shehia s = new Shehia();
        District r = ds.get();
        s.setDistrict(r);
        s.setShehiaCode(sh.getShehiaCode());
        s.setShehiaName(sh.getShehiaName());
        shehiaRepository.save(s);

        ShehiaResponseDto responseDto = new ShehiaResponseDto();
        responseDto.setShehiaId(s.getShehiaId());
        responseDto.setShehiaCode(s.getShehiaCode());
        responseDto.setShehiaName(s.getShehiaName());
        responseDto.setDistrictId(s.getDistrict().getDistrictId());
        responseDto.setCreatedDate(s.getCreatedDate());
        responseDto.setCreatedBy(s.getCreatedBy());
        responseDto.setModifiedDate(s.getModifiedDate());
        responseDto.setModifiedBy(s.getModifiedBy());

        return  ResponseEntity.ok(responseDto);

    }

    public Optional<Shehia> getShehiaById(String shehiaId){
        return shehiaRepository.findById(shehiaId);
    }


    public void deleteShehia(String shehiaId){
        shehiaRepository.deleteById(shehiaId);
    }

    public void updateShehia(String shehiaId, Shehia shehia) {
        shehiaRepository.findById(shehiaId)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Shehia with Id "+ shehiaId + " does not exist"
                ));
        shehia.setShehiaId(shehiaId);
        shehiaRepository.save(shehia);

    }


}


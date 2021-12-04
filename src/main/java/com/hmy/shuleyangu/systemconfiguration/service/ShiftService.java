package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.ShiftRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShiftResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Shift;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.ShiftRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {
    private ModelMapper modelMapper;
    @Autowired
    private ShiftRepository shiftRepository;
    public ShiftService(ModelMapper modelMapper, ShiftRepository shiftRepository){
        this.modelMapper = modelMapper;
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> findShifts(PageRequest pageRequest) {
        return shiftRepository.findAll(pageRequest).getContent();
    }
    public ResponseEntity<ShiftResponseDto> addNewShift(ShiftRequestDto shiftRequestDto) {
        Shift shift = new Shift();
        shift.setShiftName(shiftRequestDto.getShiftName());
        shift.setStatus(shiftRequestDto.getStatus());
        shiftRepository.save(shift);

        ShiftResponseDto responseDto = new ShiftResponseDto();
        responseDto.setShiftId(shift.getShiftId());
        responseDto.setShiftName(shift.getShiftName());
        responseDto.setStatus(shift.getStatus());
        responseDto.setCreatedDate(shift.getCreatedDate());
        responseDto.setCreatedBy(shift.getCreatedBy());
        responseDto.setModifiedDate(shift.getModifiedDate());
        responseDto.setModifiedBy(shift.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    public Optional<Shift> getShiftById(String shiftId){
        return shiftRepository.findById(shiftId);
    }

    public void deleteShift(String shiftId){
        shiftRepository.deleteById(shiftId);
    }

}

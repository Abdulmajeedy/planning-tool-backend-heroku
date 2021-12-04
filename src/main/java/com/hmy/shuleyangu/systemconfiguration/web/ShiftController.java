package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.ShiftRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ShiftResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Shift;
import com.hmy.shuleyangu.systemconfiguration.repository.ShiftRepository;
import com.hmy.shuleyangu.systemconfiguration.service.ShiftService;
import com.hmy.shuleyangu.systemconfiguration.utils.ApiResponse;
import com.hmy.shuleyangu.systemconfiguration.web.api.ShiftApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ShiftController implements ShiftApi {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private ShiftRepository shiftRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ShiftController(ShiftService shiftService, ModelMapper modelMapper){
        this.shiftService=shiftService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<ShiftResponseDto>> getShifts(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Shift> shifts = shiftService.findShifts(pageRequest);
        List<ShiftResponseDto> shiftResponseDto = new ArrayList<>();
        for(Shift sh:shifts)
        {
            ShiftResponseDto responseDto = new ShiftResponseDto();
            responseDto.setShiftId(sh.getShiftId());
            responseDto.setShiftName(sh.getShiftName());
            responseDto.setStatus(sh.getStatus());
            responseDto.setCreatedDate(sh.getCreatedDate());
            responseDto.setCreatedBy(sh.getCreatedBy());
            responseDto.setModifiedDate(sh.getModifiedDate());
            responseDto.setModifiedBy(sh.getModifiedBy());
            shiftResponseDto.add(responseDto);
        }
        return ResponseEntity.ok(shiftResponseDto);
    }

    public ResponseEntity<ShiftResponseDto> registerNewShift(ShiftRequestDto shiftRequestDto){
        return shiftService.addNewShift(shiftRequestDto);
    }

    public ResponseEntity<ShiftResponseDto> getShiftById(String shiftId) {
        {
            Optional<Shift> sh = shiftService.getShiftById(shiftId);
            if(!sh.isPresent())
            {
                return new ResponseEntity(ApiResponse.error("Invalid ID",null), HttpStatus.NOT_FOUND);
            }
            else {
                Shift s = sh.get();
                ShiftResponseDto responseDto = new ShiftResponseDto();
                responseDto.setShiftId(s.getShiftId());
                responseDto.setShiftName(s.getShiftName());
                responseDto.setStatus(s.getStatus());
                responseDto.setCreatedDate(s.getCreatedDate());
                responseDto.setCreatedBy(s.getCreatedBy());
                responseDto.setModifiedDate(s.getModifiedDate());
                responseDto.setModifiedBy(s.getModifiedBy());
                responseDto.setStatus(s.getStatus());
                return ResponseEntity.ok(responseDto);
            }}
    }
    public void deleteById(String shiftId){
        shiftService.deleteShift(shiftId);

    }

}

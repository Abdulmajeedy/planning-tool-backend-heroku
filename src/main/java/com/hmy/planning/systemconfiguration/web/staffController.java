package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hmy.planning.systemconfiguration.dto.staffRequestDto;
import com.hmy.planning.systemconfiguration.dto.staffResponseDto;
import com.hmy.planning.systemconfiguration.models.staff;
import com.hmy.planning.systemconfiguration.service.staffService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.staffApi;

@RestController
public class staffController implements staffApi {

    private staffService staffServices;
    private ModelMapper modelMapper;

    @Autowired
    public staffController(staffService staffServices, ModelMapper modelMapper) {

        this.staffServices = staffServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<staffResponseDto>> getStaff(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<staff> staff = staffServices.findAllStaff(pageRequest);
        List<staffResponseDto> actDto = new ArrayList<>();
        for (staff st : staff) {
            staffResponseDto responseDto = new staffResponseDto();

            responseDto.setStaffID(st.getStaffID());
            responseDto.setFirstname(st.getFirstName());
            responseDto.setMiddlename(st.getMiddleName());
            responseDto.setLastname(st.getLastName());
            responseDto.setEmail(st.getEmail());
            responseDto.setPhone(st.getPhone());
            responseDto.setStaffCode(st.getStaffCode());
            // responseDto.setOfficeID(act.getOrgStructures().getOfficeID());
            responseDto.setStatus(st.getStatus());
            responseDto.setStatus(st.getStatus());
            responseDto.setCreatedDate(st.getCreatedDate());
            responseDto.setCreatedBy(st.getCreatedBy());
            responseDto.setModifiedDate(st.getModifiedDate());
            responseDto.setModifiedBy(st.getModifiedBy());
            actDto.add(responseDto);
        }
        return ResponseEntity.ok(actDto);
    }

    @Override
    public ResponseEntity<staffResponseDto> registerNewStaff(staffRequestDto reqStaff) {
        return staffServices.addNewStaff(reqStaff);
    }

    @Override
    public ResponseEntity<staffResponseDto> getStaffById(String staffID) {
        Optional<staff> orgStr = staffServices.getStaffById(staffID);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid Activity Code", null), HttpStatus.NOT_FOUND);
        }
        staff bg = orgStr.get();
        staffResponseDto responseDto = new staffResponseDto();

        responseDto.setStaffID(bg.getStaffID());
        responseDto.setFirstname(bg.getFirstName());
        responseDto.setMiddlename(bg.getMiddleName());
        responseDto.setLastname(bg.getLastName());
        responseDto.setEmail(bg.getEmail());
        responseDto.setPhone(bg.getPhone());
        responseDto.setStaffCode(bg.getStaffCode());
        responseDto.setStatus(bg.getStatus());
        responseDto.setCreatedDate(bg.getCreatedDate());
        responseDto.setCreatedBy(bg.getCreatedBy());
        responseDto.setModifiedDate(bg.getModifiedDate());
        responseDto.setModifiedBy(bg.getModifiedBy());

        return ResponseEntity.ok(responseDto);
    }

    @Override
    public void deleteById(String staffID) {
        staffServices.deleteStaff(staffID);

    }

    @Override
    public ResponseEntity updateStaff(String staffID, staff reqStaff) {
        Optional<staff> budPer = staffServices.getStaffById(staffID);
        staffServices.updateStaff(staffID, reqStaff);
        staff stf = budPer.get();
        staffResponseDto responseDto = new staffResponseDto();
        responseDto.setStaffID(stf.getStaffID());
        responseDto.setFirstname(stf.getFirstName());
        responseDto.setMiddlename(stf.getMiddleName());
        responseDto.setLastname(stf.getLastName());
        responseDto.setEmail(stf.getEmail());
        responseDto.setPhone(stf.getPhone());
        responseDto.setStaffCode(stf.getStaffCode());
        responseDto.setCreatedDate(stf.getCreatedDate());
        responseDto.setCreatedBy(stf.getCreatedBy());
        responseDto.setModifiedDate(stf.getModifiedDate());
        responseDto.setModifiedBy(stf.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity updateStatus(String staffID) {
        return ResponseEntity.ok().body(staffServices.updateStatus(staffID));
    }

}

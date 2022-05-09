package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.orgStructureRequestDto;
import com.hmy.planning.systemconfiguration.dto.orgStructureResponseDto;
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.repository.OrgStructureRepository;
import com.hmy.planning.systemconfiguration.service.orgStructureService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.orgStructureApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class orgStructureController implements orgStructureApi {

    private orgStructureService orgStructureService;
    private OrgStructureRepository orgStructureRepo;
    private ModelMapper modelMapper;

    @Autowired
    public orgStructureController(orgStructureService orgStructureService, ModelMapper modelMapper) {

        this.orgStructureService = orgStructureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<orgStructureResponseDto>> getOrgStructure(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<orgStructure> role = orgStructureService.findAllOrgStructures(pageRequest);
        List<orgStructureResponseDto> roleDto = new ArrayList<>();
        for (orgStructure rol : role) {
            orgStructureResponseDto responseDto = new orgStructureResponseDto();
            responseDto.setOfficeID(rol.getOfficeID());
            responseDto.setOfficeShortCode(rol.getOfficeShortName());
            responseDto.setOfficeName(rol.getOfficeName());
            responseDto.setReportTo(rol.getReportTo());
            responseDto.setStatus(rol.getStatus());
            responseDto.setCreatedDate(rol.getCreatedDate());
            responseDto.setCreatedBy(rol.getCreatedBy());
            responseDto.setModifiedDate(rol.getModifiedDate());
            responseDto.setModifiedBy(rol.getModifiedBy());
            roleDto.add(responseDto);
        }
        return ResponseEntity.ok(roleDto);
    }

    @Override
    public ResponseEntity<orgStructureResponseDto> registerNewOrgStructure(orgStructureRequestDto orgStructure) {
        return orgStructureService.addNewOrgStructure(orgStructure);
    }

    @Override
    public ResponseEntity<orgStructureResponseDto> getOrgStructureById(String officeID) {
        Optional<orgStructure> orgStr = orgStructureService.getOrgStaructureById(officeID);
        if (!orgStr.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            orgStructure org = orgStr.get();
            orgStructureResponseDto responseDto = new orgStructureResponseDto();
            responseDto.setOfficeID(org.getOfficeID());
            responseDto.setOfficeShortCode(org.getOfficeShortName());
            responseDto.setOfficeName(org.getOfficeName());
            responseDto.setReportTo(org.getReportTo());
            responseDto.setStatus(org.getStatus());
            responseDto.setCreatedDate(org.getCreatedDate());
            responseDto.setCreatedBy(org.getCreatedBy());
            responseDto.setModifiedDate(org.getModifiedDate());
            responseDto.setModifiedBy(org.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String officeID) {
        orgStructureService.deleteOrgStructure(officeID);
    }

    @Override
    public ResponseEntity updateOrgStructure(String officeID, orgStructure orgStructure) {
        Optional<orgStructure> orgStr = orgStructureService.getOrgStaructureById(officeID);
        orgStructureService.updateOrgStructure(officeID, orgStructure);
        orgStructure org = orgStr.get();
        orgStructureResponseDto responseDto = new orgStructureResponseDto();

        responseDto.setOfficeID(org.getOfficeID());
        responseDto.setOfficeShortCode(org.getOfficeShortName());
        responseDto.setOfficeName(org.getOfficeName());
        responseDto.setReportTo(org.getReportTo());
        responseDto.setStatus(org.getStatus());
        responseDto.setCreatedDate(org.getCreatedDate());
        responseDto.setCreatedBy(org.getCreatedBy());
        responseDto.setModifiedDate(org.getModifiedDate());
        responseDto.setModifiedBy(org.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}

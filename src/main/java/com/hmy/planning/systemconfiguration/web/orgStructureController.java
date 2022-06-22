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
        return ResponseEntity.ok(orgStructureService.findAllOrgStructures(page, size));
    }

    @Override
    public ResponseEntity<orgStructureResponseDto> registerNewOrgStructure(orgStructureRequestDto orgStructure) {
        return orgStructureService.addNewOrgStructure(orgStructure);
    }

    @Override
    public ResponseEntity<orgStructureResponseDto> getOrgStructureById(String officeID) {
        return ResponseEntity.ok(orgStructureService.getOrgStaructureById(officeID));
    }

    @Override
    public void deleteById(String officeID) {
        orgStructureService.deleteOrgStructure(officeID);
    }

    @Override
    public ResponseEntity updateOrgStructure(String officeID, orgStructure orgStructure) {
        return ResponseEntity.ok(orgStructureService.updateOrgStructure(officeID, orgStructure));
    }

    @Override
    public ResponseEntity updateStatus(String officeID) {
        return ResponseEntity.ok().body(orgStructureService.updateStatus(officeID));
    }

}

package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.Roles;
import com.hmy.planning.systemconfiguration.repository.RoleRepository;
import com.hmy.planning.systemconfiguration.service.rolesService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.rolesApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class roleController implements rolesApi {

    private rolesService rolesService;
    private RoleRepository roleRepo;
    private ModelMapper modelMapper;

    @Autowired
    public roleController(rolesService rolesService, ModelMapper modelMapper) {

        this.rolesService = rolesService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<roleResponseDto>> getRole(int page, int size) {

        return ResponseEntity.ok(rolesService.findAllRoles(page, size));
    }

    public ResponseEntity<roleResponseDto> registerNewRole(roleRequestDto reqRoles) {
        return rolesService.addNewRole(reqRoles);
    }

    @Override
    public ResponseEntity<roleResponseDto> getRoleById(String roleCode) {
        return ResponseEntity.ok(rolesService.getRoleById(roleCode));
    }

    public void deleteById(String roleCode) {
        rolesService.deleteRoles(roleCode);
    }

    @Override
    public ResponseEntity updateRole(String roleCode, Roles reqRoles) {
        return ResponseEntity.ok(rolesService.updateRole(roleCode, reqRoles));
    }

    @Override
    public ResponseEntity updateStatus(String roleCode) {
        return ResponseEntity.ok().body(rolesService.updateStatus(roleCode));
    }
}

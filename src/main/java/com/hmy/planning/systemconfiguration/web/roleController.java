package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.roles;
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
        PageRequest pageRequest = PageRequest.of(page, size);
        List<roles> role = rolesService.findAllRoles(pageRequest);
        List<roleResponseDto> roleDto = new ArrayList<>();
        for (roles rol : role) {
            roleResponseDto responseDto = new roleResponseDto();
            responseDto.setRoleCode(rol.getRoleCode());
            responseDto.setRole(rol.getRole());
            responseDto.setDescription(rol.getDescription());
            responseDto.setStatus(rol.getStatus());
            responseDto.setCreatedDate(rol.getCreatedDate());
            responseDto.setCreatedBy(rol.getCreatedBy());
            responseDto.setModifiedDate(rol.getModifiedDate());
            responseDto.setModifiedBy(rol.getModifiedBy());
            roleDto.add(responseDto);
        }
        return ResponseEntity.ok(roleDto);
    }

    public ResponseEntity<roleResponseDto> registerNewRole(roleRequestDto reqRoles) {
        return rolesService.addNewRole(reqRoles);
    }

    @Override
    public ResponseEntity<roleResponseDto> getRoleById(String roleCode) {
        Optional<roles> role = rolesService.getRoleById(roleCode);
        if (!role.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            roles rol = role.get();
            roleResponseDto responseDto = new roleResponseDto();
            responseDto.setRoleCode(rol.getRoleCode());
            responseDto.setRole(rol.getRole());
            responseDto.setDescription(rol.getDescription());
            responseDto.setStatus(rol.getStatus());
            responseDto.setCreatedDate(rol.getCreatedDate());
            responseDto.setCreatedBy(rol.getCreatedBy());
            responseDto.setModifiedDate(rol.getModifiedDate());
            responseDto.setModifiedBy(rol.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    public void deleteById(String roleCode) {
        rolesService.deleteRoles(roleCode);
    }

    @Override
    public ResponseEntity updateRole(String roleCode, roles reqRoles) {
        Optional<roles> roles = rolesService.getRoleById(roleCode);
        rolesService.updateRole(roleCode, reqRoles);
        roles rol = roles.get();
        roleResponseDto responseDto = new roleResponseDto();
        responseDto.setRoleCode(rol.getRoleCode());
        responseDto.setRole(rol.getRole());
        responseDto.setDescription(rol.getDescription());
        responseDto.setStatus(rol.getStatus());
        responseDto.setCreatedDate(rol.getCreatedDate());
        responseDto.setCreatedBy(rol.getCreatedBy());
        responseDto.setModifiedDate(rol.getModifiedDate());
        responseDto.setModifiedBy(rol.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }
}

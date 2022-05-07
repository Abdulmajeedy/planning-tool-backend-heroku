package com.hmy.planning.systemconfiguration.web;

import java.util.List;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.roles;
import com.hmy.planning.systemconfiguration.repository.roleRepository;
import com.hmy.planning.systemconfiguration.service.rolesService;
import com.hmy.planning.systemconfiguration.web.api.rolesApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class roleController implements rolesApi {

    private rolesService rolesService;
    private roleRepository roleRepo;
    private ModelMapper modelMapper;

    @Autowired
    public roleController(rolesService rolesService, ModelMapper modelMapper) {

        this.rolesService = rolesService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<roleResponseDto>> getRole(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<roleResponseDto> registerNewRole(roleRequestDto academicYear) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<roleResponseDto> getRoleById(String academicYearId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(String academicYearId) {
        // TODO Auto-generated method stub

    }

    @Override
    public ResponseEntity updateRole(String academicYearId, roles reqRoles) {
        // TODO Auto-generated method stub
        return null;
    }

}

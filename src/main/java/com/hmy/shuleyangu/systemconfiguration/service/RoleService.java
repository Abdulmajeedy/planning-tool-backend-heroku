package com.hmy.shuleyangu.systemconfiguration.service;

import com.hmy.shuleyangu.systemconfiguration.dto.RoleRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RoleResponseDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.ZoneResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Roles;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import com.hmy.shuleyangu.systemconfiguration.repository.RoleRepository;
import com.hmy.shuleyangu.systemconfiguration.repository.ZoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class RoleService {
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    public RoleService(ModelMapper modelMapper, RoleRepository roleRepository){
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public List<Roles> findAllRoles(PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest).getContent();
    }

    public ResponseEntity<RoleResponseDto> addNewRole(RoleRequestDto rol) {
        Roles r = new Roles();
        r.setRoleName(rol.getRoleName());
        r.setStatus(rol.getStatus());
        roleRepository.save(r);

        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setRoleId(r.getRoleId());
        responseDto.setRoleName(r.getRoleName());
        responseDto.setStatus(r.getStatus());
        responseDto.setCreatedDate(r.getCreatedDate());
        responseDto.setCreatedBy(r.getCreatedBy());
        responseDto.setModifiedDate(r.getModifiedDate());
        responseDto.setModifiedBy(r.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

}

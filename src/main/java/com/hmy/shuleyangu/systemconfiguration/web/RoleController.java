package com.hmy.shuleyangu.systemconfiguration.web;

import com.hmy.shuleyangu.systemconfiguration.dto.RoleRequestDto;
import com.hmy.shuleyangu.systemconfiguration.dto.RoleResponseDto;
import com.hmy.shuleyangu.systemconfiguration.models.Roles;
import com.hmy.shuleyangu.systemconfiguration.repository.RoleRepository;
import com.hmy.shuleyangu.systemconfiguration.service.RoleService;
import com.hmy.shuleyangu.systemconfiguration.web.api.RoleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController implements RoleApi {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    public ResponseEntity<List<RoleResponseDto>> getRoles(int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Roles> roles = roleService.findAllRoles(pageRequest);
        List<RoleResponseDto> rol = new ArrayList<>();
        for(Roles r:roles)
        {
            RoleResponseDto responseDto = new RoleResponseDto();
            responseDto.setRoleId(r.getRoleId());
            responseDto.setRoleName(r.getRoleName());
            responseDto.setStatus(r.getStatus());
            responseDto.setCreatedDate(r.getCreatedDate());
            responseDto.setCreatedBy(r.getCreatedBy());
            responseDto.setModifiedDate(r.getModifiedDate());
            responseDto.setModifiedBy(r.getModifiedBy());
            rol.add(responseDto);
        }
        return ResponseEntity.ok(rol);
    }

    public ResponseEntity<RoleResponseDto> registerNewRole(RoleRequestDto rrd) {
        return roleService.addNewRole(rrd);
    }
}

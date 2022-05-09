package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.roles;
import com.hmy.planning.systemconfiguration.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class rolesService {
    @Autowired
    private RoleRepository rolesRepo;

    @Autowired
    public rolesService(RoleRepository roleRepo) {
        this.rolesRepo = rolesRepo;

    }

    public List<roles> findAllRoles(PageRequest pageRequest) {
        return rolesRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<roleResponseDto> addNewRole(roleRequestDto reqRoles) {
        roles role = new roles();
        role.setRole(reqRoles.getRole());
        role.setDescription(reqRoles.getDescription());
        role.setStatus(reqRoles.getStatus());
        rolesRepo.save(role);

        roleResponseDto roleDto = new roleResponseDto();
        roleDto.setRoleCode(role.getRoleCode());
        roleDto.setRole(role.getRole());
        roleDto.setDescription(role.getDescription());
        roleDto.setStatus(role.getStatus());
        roleDto.setCreatedDate(role.getCreatedDate());
        roleDto.setCreatedBy(role.getCreatedBy());
        roleDto.setModifiedDate(role.getModifiedDate());
        roleDto.setModifiedBy(role.getModifiedBy());
        return ResponseEntity.ok(roleDto);
    }

    public void deleteRoles(String roleCode) {

        rolesRepo.deleteById(roleCode);
    }

    public Optional<roles> getRoleById(String roleCode) {
        return rolesRepo.findById(roleCode);
    }

    public void updateRole(String roleCode, roles reqRoles) {
        rolesRepo.findById(roleCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Role  with Code " + roleCode + " does not exist"));

        reqRoles.setRoleCode(roleCode);
        roles role = new roles();
        role.setCreatedBy(reqRoles.getCreatedBy());
        role.setCreatedDate(reqRoles.getCreatedDate());
        role.setModifiedBy(reqRoles.getModifiedBy());
        rolesRepo.save(reqRoles);

    }

}

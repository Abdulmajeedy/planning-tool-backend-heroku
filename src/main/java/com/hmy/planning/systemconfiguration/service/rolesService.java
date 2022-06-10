package com.hmy.planning.systemconfiguration.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.Roles;
import com.hmy.planning.systemconfiguration.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class rolesService {
    @Autowired
    private RoleRepository rolesRepo;

    @Autowired
    public rolesService(RoleRepository roleRepo) {
        this.rolesRepo = rolesRepo;

    }

    public List<Roles> findAllRoles(PageRequest pageRequest) {
        return rolesRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<roleResponseDto> addNewRole(roleRequestDto reqRoles) {
        Roles role = new Roles();
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

    public Optional<Roles> getRoleById(String roleCode) {
        return rolesRepo.findById(roleCode);
    }

    public void updateRole(String roleCode, Roles reqRoles) {
        rolesRepo.findById(roleCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Role  with Code " + roleCode + " does not exist"));

        reqRoles.setRoleCode(roleCode);
        Roles role = new Roles();
        role.setCreatedBy(reqRoles.getCreatedBy());
        role.setCreatedDate(reqRoles.getCreatedDate());
        role.setModifiedBy(reqRoles.getModifiedBy());
        rolesRepo.save(reqRoles);

    }

    public Map<String, Boolean> updateStatus(String roleCode) {
        Optional<Roles> bp = rolesRepo.findById(roleCode);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        rolesRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    public Optional<Roles> getRolCode(String roleCode) {
        return rolesRepo.findById(roleCode);
    }

}

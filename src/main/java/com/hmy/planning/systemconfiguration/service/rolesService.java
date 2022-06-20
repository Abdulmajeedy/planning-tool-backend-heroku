package com.hmy.planning.systemconfiguration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.Role;

import com.hmy.planning.systemconfiguration.dto.roleRequestDto;
import com.hmy.planning.systemconfiguration.dto.roleResponseDto;
import com.hmy.planning.systemconfiguration.models.Roles;
import com.hmy.planning.systemconfiguration.repository.RoleRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class rolesService {
    @Autowired
    private RoleRepository rolesRepo;
    private final ModelMapper modelmapper;

    public List<roleResponseDto> findAllRoles(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Roles> role = rolesRepo.findAll(pageRequest).getContent();
        List<roleResponseDto> roleDto = new ArrayList<>();
        for (Roles rol : role) {
            roleResponseDto responseDto = modelmapper.map(rol, roleResponseDto.class);
            roleDto.add(responseDto);
        }
        return roleDto;
    }

    public ResponseEntity<roleResponseDto> addNewRole(roleRequestDto reqRoles) {
        Roles role = modelmapper.map(reqRoles, Roles.class);
        rolesRepo.save(role);

        roleResponseDto rol = modelmapper.map(role, roleResponseDto.class);
        return ResponseEntity.ok(rol);

    }

    public void deleteRoles(String roleCode) {

        rolesRepo.deleteById(roleCode);
    }

    public roleResponseDto getRoleById(String roleCode) {
        Optional<Roles> roles = rolesRepo.findById(roleCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + roleCode + "is not Found");
        } else {
            Roles rol = roles.get();
            roleResponseDto responseDto = modelmapper.map(rol, roleResponseDto.class);
            return responseDto;
        }
    }

    public ResponseEntity<roleResponseDto> updateRole(String roleCode, Roles reqRoles) {

        Optional<Roles> roles = rolesRepo.findById(roleCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + roleCode + "is not Found");
        }
        Roles rol = modelmapper.map(reqRoles, Roles.class);
        rol.setRoleCode(roleCode);
        rolesRepo.save(rol);

        roleResponseDto roltDto = modelmapper.map(rol, roleResponseDto.class);
        return ResponseEntity.ok(roltDto);

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

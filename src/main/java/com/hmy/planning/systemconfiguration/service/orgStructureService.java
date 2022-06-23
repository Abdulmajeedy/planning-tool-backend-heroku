package com.hmy.planning.systemconfiguration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.orgStructureRequestDto;
import com.hmy.planning.systemconfiguration.dto.orgStructureResponseDto;
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.repository.OrgStructureRepository;

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
public class orgStructureService {

    @Autowired
    private OrgStructureRepository orgStructureRepo;
    private final ModelMapper modelmapper;

    public List<orgStructureResponseDto> findAllOrgStructures(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<orgStructure> orgStructures = orgStructureRepo.findAll(pageRequest).getContent();
        List<orgStructureResponseDto> roleDto = new ArrayList<>();
        for (orgStructure orgStructure : orgStructures) {
            orgStructureResponseDto responseDto = modelmapper.map(orgStructure, orgStructureResponseDto.class);
            roleDto.add(responseDto);
        }
        return roleDto;
    }

    public ResponseEntity<orgStructureResponseDto> addNewOrgStructure(orgStructureRequestDto reqOrg) {
        orgStructure orgStructures = modelmapper.map(reqOrg, orgStructure.class);
        orgStructureRepo.save(orgStructures);

        orgStructureResponseDto orgStr = modelmapper.map(orgStructures, orgStructureResponseDto.class);
        return ResponseEntity.ok(orgStr);
    }

    public void deleteOrgStructure(String officeID) {

        orgStructureRepo.deleteById(officeID);
    }

    public orgStructureResponseDto getOrgStaructureById(String officeID) {
        Optional<orgStructure> office = orgStructureRepo.findById(officeID);
        if (!office.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + officeID + "is not Found");
        } else {
            orgStructure rol = office.get();
            orgStructureResponseDto responseDto = modelmapper.map(rol, orgStructureResponseDto.class);
            return responseDto;
        }

    }

    public ResponseEntity<orgStructureResponseDto> updateOrgStructure(String officeID, orgStructureRequestDto reqOrg) {
        Optional<orgStructure> office = orgStructureRepo.findById(officeID);
        if (!office.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + officeID + "is not Found");
        }
        orgStructure ofc = modelmapper.map(reqOrg, orgStructure.class);
        ofc.setOfficeID(officeID);
        orgStructureRepo.save(ofc);

        orgStructureResponseDto roltDto = modelmapper.map(ofc, orgStructureResponseDto.class);
        return ResponseEntity.ok(roltDto);

    }

    public Optional<orgStructure> getOrgStructureID(String officeID) {
        return orgStructureRepo.findById(officeID);
    }

    public Map<String, Boolean> updateStatus(String officeID) {
        Optional<orgStructure> bp = orgStructureRepo.findById(officeID);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        orgStructureRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }
}

package com.hmy.planning.systemconfiguration.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.orgStructureRequestDto;
import com.hmy.planning.systemconfiguration.dto.orgStructureResponseDto;
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.repository.OrgStructureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class orgStructureService {

    @Autowired
    private OrgStructureRepository orgStructureRepo;

    @Autowired
    public orgStructureService(OrgStructureRepository orgStructureRepo) {
        this.orgStructureRepo = orgStructureRepo;

    }

    public List<orgStructure> findAllOrgStructures(PageRequest pageRequest) {
        return orgStructureRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<orgStructureResponseDto> addNewOrgStructure(orgStructureRequestDto reqOrg) {
        orgStructure org = new orgStructure();
        org.setOfficeName(reqOrg.getOfficeName());
        org.setOfficeShortName(reqOrg.getOfficeShortName());
        org.setOfficeCode(reqOrg.getOfficeCode());
        org.setReportTo(reqOrg.getReportTo());
        org.setStatus(reqOrg.getStatus());
        orgStructureRepo.save(org);

        orgStructureResponseDto orgDto = new orgStructureResponseDto();
        orgDto.setOfficeName(reqOrg.getOfficeName());
        orgDto.setOfficeShortName(reqOrg.getOfficeShortName());
        orgDto.setOfficeCode(reqOrg.getOfficeCode());
        orgDto.setReportTo(reqOrg.getReportTo());
        orgDto.setStatus(reqOrg.getStatus());
        orgDto.setCreatedDate(reqOrg.getCreatedDate());
        orgDto.setCreatedBy(reqOrg.getCreatedBy());
        orgDto.setModifiedDate(reqOrg.getModifiedDate());
        orgDto.setModifiedBy(reqOrg.getModifiedBy());
        return ResponseEntity.ok(orgDto);
    }

    public void deleteOrgStructure(String officeID) {

        orgStructureRepo.deleteById(officeID);
    }

    public Optional<orgStructure> getOrgStaructureById(String officeID) {
        return orgStructureRepo.findById(officeID);
    }

    public void updateOrgStructure(String officeID, orgStructure reqOrg) {
        orgStructureRepo.findById(
                officeID)
                .orElseThrow(() -> new IllegalStateException(
                        "Office  with ID " + officeID + " does not exist"));

        reqOrg.setOfficeID(officeID);
        orgStructure org = new orgStructure();
        org.setCreatedBy(reqOrg.getCreatedBy());
        org.setCreatedDate(reqOrg.getCreatedDate());
        org.setModifiedBy(reqOrg.getModifiedBy());
        orgStructureRepo.save(reqOrg);

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

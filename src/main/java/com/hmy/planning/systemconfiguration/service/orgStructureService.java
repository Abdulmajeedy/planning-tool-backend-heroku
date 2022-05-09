package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.orgStructureRequestDto;
import com.hmy.planning.systemconfiguration.dto.orgStructureResponseDto;
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.repository.OrgStructureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        orgStructureResponseDto roleDto = new orgStructureResponseDto();
        roleDto.setOfficeName(reqOrg.getOfficeName());
        roleDto.setOfficeShortName(reqOrg.getOfficeShortName());
        roleDto.setOfficeCode(reqOrg.getOfficeCode());
        roleDto.setReportTo(reqOrg.getReportTo());
        roleDto.setStatus(reqOrg.getStatus());
        roleDto.setCreatedDate(reqOrg.getCreatedDate());
        roleDto.setCreatedBy(reqOrg.getCreatedBy());
        roleDto.setModifiedDate(reqOrg.getModifiedDate());
        roleDto.setModifiedBy(reqOrg.getModifiedBy());
        return ResponseEntity.ok(roleDto);
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
                        "Role  with Code " + officeID + " does not exist"));

        reqOrg.setOfficeID(officeID);
        orgStructure org = new orgStructure();
        org.setCreatedBy(reqOrg.getCreatedBy());
        org.setCreatedDate(reqOrg.getCreatedDate());
        org.setModifiedBy(reqOrg.getModifiedBy());
        orgStructureRepo.save(reqOrg);

    }

}

package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hmy.planning.systemconfiguration.dto.staffRequestDto;
import com.hmy.planning.systemconfiguration.dto.staffResponseDto;
import com.hmy.planning.systemconfiguration.models.login;
import com.hmy.planning.systemconfiguration.models.orgStructure;
import com.hmy.planning.systemconfiguration.models.Roles;
import com.hmy.planning.systemconfiguration.models.staff;
import com.hmy.planning.systemconfiguration.repository.LoginRepository;
import com.hmy.planning.systemconfiguration.repository.StaffRepository;

import lombok.Data;

@Service
@Data
public class staffService {

    @Autowired
    private final StaffRepository staffRepo;
    private final LoginRepository loginRepo;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private final rolesService roleServices;
    private final QuaterPeriodService quaterPd;
    private final orgStructureService orgServices;

    private ModelMapper modelMapper;

    public List<staff> findAllStaff(PageRequest pageRequest) {
        return staffRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<staffResponseDto> addNewStaff(staffRequestDto reqStaff) {
        Optional<orgStructure> orgStructure = orgServices.getOrgStructureID(reqStaff.getOfficeID());
        Optional<Roles> role = roleServices.getRolCode(reqStaff.getRoleCode());

        if (!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!orgStructure.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Roles roleObj = new Roles();
        roleObj.setRoleCode(reqStaff.getRoleCode());
        ;

        orgStructure orgObj = new orgStructure();
        orgObj.setOfficeID(reqStaff.getOfficeID());

        staff act = new staff();
        login logn = new login();
        logn.setEmail(reqStaff.getEmail());
        logn.setPassword(reqStaff.getLastname());
        logn.setLogins(0);
        logn.setStatus(1);
        logn.setRole(roleObj);
        loginRepo.save(logn);

        login lo = new login();
        lo.setLoginCode(logn.getLoginCode());

        act.setFirstName(reqStaff.getFirstname());
        act.setMiddleName(reqStaff.getMiddlename());
        act.setLastName(reqStaff.getLastname());
        act.setEmail(reqStaff.getEmail());
        act.setOrgStructure(orgObj);
        act.setLogin(lo);
        act.setPhone(reqStaff.getPhone());
        act.setStaffCode(reqStaff.getStaffCode());
        // act.setOrgStructures(orgObj);
        act.setStatus(reqStaff.getStatus());
        staffRepo.save(act);
        sendEmail(reqStaff);

        staffResponseDto actDto = new staffResponseDto();
        actDto.setStaffID(act.getStaffID());
        actDto.setStaffCode(act.getStaffCode());
        actDto.setFirstname(act.getFirstName());
        actDto.setMiddlename(act.getMiddleName());
        actDto.setLastname(act.getLastName());
        actDto.setEmail(logn.getEmail());
        actDto.setStaffCode(act.getStaffCode());
        act.setPhone(act.getPhone());
        actDto.setOfficeID(act.getOrgStructure().getOfficeID());
        actDto.setStatus(act.getStatus());
        actDto.setStatus(act.getStatus());
        actDto.setCreatedDate(act.getCreatedDate());
        actDto.setCreatedBy(act.getCreatedBy());
        actDto.setModifiedDate(act.getModifiedDate());
        actDto.setModifiedBy(act.getModifiedBy());
        return ResponseEntity.ok(actDto);
    }

    public void sendEmail(staffRequestDto staff) {
        String from = "abdulmajeedhajji@gmail.com";
        String to = staff.getEmail();
        String FullName = staff.getFirstname() + " " + staff.getMiddlename() + " " + staff.getLastname();
        String Email = staff.getEmail();
        String Password = staff.getLastname();
        String Subject = "Successfully User Registration";
        String messageText = "Dear " + FullName + "<br>"
                + "Congratulations! You have been registered into Planning Tool Platform"
                + "The following are your platform credentails.Dont forget to change the Password"
                + "once you have successfully login for the first Time"
                + "Email: " + Email + "<br>"
                + "Password: " + Password + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "Department of Planning"
                + "State University of Zanzibar";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(Subject);
        message.setText(messageText);
        mailSender.send(message);
    }

    public void deleteStaff(String staffID) {

        staffRepo.deleteById(staffID);
    }

    public Optional<staff> getStaffById(String staffID) {
        return staffRepo.findById(staffID);
    }

    public void updateStaff(String staffID, staff reqStaff) {
        staffRepo.findById(staffID)
                .orElseThrow(() -> new IllegalStateException(
                        "Office  with ID " + staffID + " does not exist"));

        reqStaff.setStaffID(staffID);
        staff acti = new staff();
        acti.setCreatedBy(reqStaff.getCreatedBy());
        acti.setCreatedDate(reqStaff.getCreatedDate());
        acti.setModifiedBy(reqStaff.getModifiedBy());
        staffRepo.save(reqStaff);
    }

    public Optional<staff> getStaffCode(String activityCode) {
        return staffRepo.findById(activityCode);
    }

    public Map<String, Boolean> updateStatus(String staffID) {
        Optional<staff> project = staffRepo.findById(staffID);
        if (!project.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (project.get().getStatus() == 1)
            project.get().setStatus(0);
        else
            project.get().setStatus(1);
        staffRepo.save(project.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    // public Long CountStaff() {
    // return staffRepo.CountStaff();
    // }

    // public List<Activity> GetActivities() {
    // return activityRepo.GetActivities();
    // }

}

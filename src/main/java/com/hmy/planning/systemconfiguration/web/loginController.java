package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.hmy.planning.systemconfiguration.dto.loginRequestDto;
import com.hmy.planning.systemconfiguration.dto.loginResponseDto;
import com.hmy.planning.systemconfiguration.models.login;
import com.hmy.planning.systemconfiguration.service.loginService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.loginApi;

@RestController
public class loginController implements loginApi {

    private loginService lognServices;
    private ModelMapper modelMapper;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder PasswordEncoder;

    @Autowired
    public loginController(loginService lognServices, ModelMapper modelMapper) {

        this.lognServices = lognServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<loginResponseDto> login(loginRequestDto loginData) {
        Optional<login> logins = lognServices.login(loginData.getEmail());
        if (!logins.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid Email", null),
                    HttpStatus.NOT_FOUND);

        }
        if (!PasswordEncoder.matches(loginData.getPassword(),
                logins.get().getPassword())) {
            return new ResponseEntity(ApiResponse.error("Invalid Password", null),
                    HttpStatus.NOT_FOUND);

        }

        login lo = logins.get();
        loginResponseDto responseDto = new loginResponseDto();
        responseDto.setLoginCode(lo.getLoginCode());
        responseDto.setEmail(lo.getEmail());
        responseDto.setFirstname(lo.getStaff().iterator().next().getFirstName());
        responseDto.setMiddlename(lo.getStaff().iterator().next().getMiddleName());
        responseDto.setLastname(lo.getStaff().iterator().next().getLastName());
        responseDto.setRoleCode(lo.getRole().getRoleCode());
        responseDto.setRoleName(lo.getRole().getRole());
        responseDto.setOfficeName(lo.getOrgStructure().getOfficeName());
        responseDto.setLogins(lo.getLogins());
        responseDto.setStatus(lo.getStatus());
        responseDto.setMessage("Login SuccessFully");

        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<loginResponseDto> getLoginById(String loginCode) {
        return ResponseEntity.ok(lognServices.getLoginById(loginCode));

    }

    @Override
    public void deleteById(String loginCode) {
        lognServices.deleteObjective(loginCode);

    }

    @Override
    public ResponseEntity updateStatus(String loginCode) {
        return ResponseEntity.ok().body(lognServices.updateStatus(loginCode));

    }

    @Override
    public ResponseEntity<List<loginResponseDto>> getAlllogin(int page, int size) {
        return ResponseEntity.ok(lognServices.findAllLogin(page, size));
    }

    @Override
    public ResponseEntity checkEmail(String loginCode) {
        return ResponseEntity.ok().body(lognServices.checkEmail(loginCode));
    }

}

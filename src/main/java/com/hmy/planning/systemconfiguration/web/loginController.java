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
            return new ResponseEntity(ApiResponse.error("Invalid Email", null), HttpStatus.NOT_FOUND);

        }
        if (!PasswordEncoder.matches(loginData.getPassword(), logins.get().getPassword())) {
            return new ResponseEntity(ApiResponse.error("Invalid Password", null), HttpStatus.NOT_FOUND);

        }

        login lo = logins.get();
        loginResponseDto responseDto = new loginResponseDto();
        responseDto.setLoginCode(lo.getLoginCode());
        responseDto.setEmail(lo.getEmail());
        responseDto.setFirstname(lo.getStaff().getFirstName());
        responseDto.setMiddlename(lo.getStaff().getMiddleName());
        responseDto.setLastname(lo.getStaff().getLastName());
        responseDto.setRoleCode(lo.getRole().getRoleCode());
        responseDto.setRoleName(lo.getRole().getRole());
        responseDto.setLogins(lo.getLogins());
        responseDto.setStatus(lo.getStatus());
        responseDto.setMessage("Login SuccessFully");

        return ResponseEntity.ok(responseDto);
    }
}

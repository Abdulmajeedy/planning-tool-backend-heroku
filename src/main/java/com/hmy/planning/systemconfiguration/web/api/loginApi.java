package com.hmy.planning.systemconfiguration.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hmy.planning.systemconfiguration.dto.loginRequestDto;
import com.hmy.planning.systemconfiguration.dto.loginResponseDto;

@CrossOrigin
@RequestMapping("/auth")
public interface loginApi {

    // @RequestMapping(value = "login/", method = RequestMethod.POST, produces =
    // "application/json")
    // public ResponseEntity<loginResponseDto> login(
    // @RequestBody loginRequestDto loginData);

}

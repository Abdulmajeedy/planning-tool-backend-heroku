package com.hmy.planning.systemconfiguration.web.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hmy.planning.systemconfiguration.dto.loginRequestDto;
import com.hmy.planning.systemconfiguration.dto.loginResponseDto;
import com.hmy.planning.systemconfiguration.models.login;

@CrossOrigin
@RequestMapping("/auth")
public interface loginApi {

    @RequestMapping(value = "login/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<loginResponseDto> login(@RequestBody loginRequestDto loginData);

    @RequestMapping(value = "/{loginCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<loginResponseDto> getLoginById(@PathVariable String loginCode);

    @DeleteMapping(path = "/{loginCode}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Login Deleted")
    public void deleteById(@PathVariable("loginCode") String loginCode);

    @RequestMapping(value = "updateStatus/{loginCode}", method = RequestMethod.GET)
    public ResponseEntity updateStatus(@PathVariable("loginCode") String loginCode);

    @RequestMapping(value = "checkEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity checkEmail(@PathVariable("email") String email);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<loginResponseDto>> getAlllogin(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size);

}

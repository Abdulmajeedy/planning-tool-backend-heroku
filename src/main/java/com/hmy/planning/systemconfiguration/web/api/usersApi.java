package com.hmy.planning.systemconfiguration.web.api;

import com.hmy.planning.systemconfiguration.dto.userDetailsRequestDto;
import com.hmy.planning.systemconfiguration.dto.usersRequestDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RequestMapping("/Authentication")
public interface usersApi {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size);

    @RequestMapping(path = "/{profileId}", method = RequestMethod.GET)
    public ResponseEntity getUserByid(@PathVariable("profileId") int profileId);

    // @RequestMapping(path = "verfy/{profilecode}", method = RequestMethod.GET)
    // public ResponseEntity verifyCodeProfile(@PathVariable("profilecode") UUID
    // profileCode) throws Exception;

    // @RequestMapping(path = "createAccount", method = RequestMethod.GET)
    // public ResponseEntity Update(@RequestBody userDetailsRequestDto
    // userdetailsRequesDto) throws Exception;

    @RequestMapping(path = "/status/{userCode}", method = RequestMethod.PUT)
    public ResponseEntity updateUserStatus(@PathVariable("userCode") int userCode);

    @RequestMapping(path = "/update/{userCode}", method = RequestMethod.PUT)
    public ResponseEntity UpdateUser(@PathVariable("userCode") int userCode,
            @RequestBody usersRequestDto profilerDto);

    // @RequestMapping(path = "/post", method = RequestMethod.POST)
    // public ResponseEntity post(@RequestBody ProfilerDto profilerDto);

}

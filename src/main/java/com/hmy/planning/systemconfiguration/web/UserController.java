package com.hmy.planning.systemconfiguration.web;

import com.hmy.planning.systemconfiguration.dto.userDetailsRequestDto;
import com.hmy.planning.systemconfiguration.dto.usersRequestDto;
import com.hmy.planning.systemconfiguration.web.api.usersApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements usersApi {

    @Override
    public ResponseEntity getAllUsers(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity getUserByid(int profileId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity updateUserStatus(int userCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity UpdateUser(int userCode, usersRequestDto profilerDto) {
        // TODO Auto-generated method stub
        return null;
    }

}

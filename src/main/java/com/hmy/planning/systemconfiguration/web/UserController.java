package com.hmy.planning.systemconfiguration.web;

import com.hmy.planning.systemconfiguration.dto.userDetailsRequestDto;
import com.hmy.planning.systemconfiguration.web.api.ProfilerDto;
import com.hmy.planning.systemconfiguration.web.api.UUID;
import com.hmy.planning.systemconfiguration.web.api.usersApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements usersApi {

    @Override
    public ResponseEntity getData(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity getByCode(UUID profilCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity getDataByid(int profileId) {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public ResponseEntity verifyCodeProfile(UUID profileCode) throws Exception
{
    // // TODO Auto-generated method stub
    // return null;
    // }

    @Override
    public ResponseEntity authUpdate(userDetailsRequestDto userdetailsDto) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity updateStatus(int profileId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity Update(int profileId, ProfilerDto profilerDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity post(ProfilerDto profilerDto) {
        // TODO Auto-generated method stub
        return null;
    }

}

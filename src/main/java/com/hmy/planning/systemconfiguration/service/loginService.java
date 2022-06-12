package com.hmy.planning.systemconfiguration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hmy.planning.systemconfiguration.dto.loginRequestDto;
import com.hmy.planning.systemconfiguration.models.login;
import com.hmy.planning.systemconfiguration.repository.LoginRepository;

public class loginService {

    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    public loginService(LoginRepository roleRepo) {
        this.loginRepo = loginRepo;

    }

    public List<login> login(String email) {
        return loginRepo.findByEmail(email);
    }

}

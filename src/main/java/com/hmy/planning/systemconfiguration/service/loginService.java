package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmy.planning.systemconfiguration.dto.loginRequestDto;
import com.hmy.planning.systemconfiguration.models.login;
import com.hmy.planning.systemconfiguration.repository.LoginRepository;

@Service
public class loginService {

    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    public loginService(LoginRepository roleRepo) {
        this.loginRepo = loginRepo;

    }

    public Optional<login> login(String email) {
        return loginRepo.findByEmail(email);
    }

}

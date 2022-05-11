package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

import java.time.Year;

@Data
public class usersResponseDto {

    private String userCode;
    private String firstName;
    private String MiddleName;
    private String lastName;
    private String email;
    private String pass;
    private String officeCode;
    private int status;
    private int roles;

}

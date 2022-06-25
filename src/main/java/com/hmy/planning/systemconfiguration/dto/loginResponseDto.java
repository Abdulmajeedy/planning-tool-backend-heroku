package com.hmy.planning.systemconfiguration.dto;

import org.springframework.http.HttpStatus;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class loginResponseDto extends Auditable<String> {

    private String loginCode;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String officeID;
    private String roleCode;
    private String roleName;
    private String officeName;
    private int status;
    private String message;
    private int logins;

}

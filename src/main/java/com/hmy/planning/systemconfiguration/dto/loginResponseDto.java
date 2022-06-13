package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class loginResponseDto {

    private String loginCode;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String roleCode;
    private String roleName;
    private int status;
    private int logins;

}

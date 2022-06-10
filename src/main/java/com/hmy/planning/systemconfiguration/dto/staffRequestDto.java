package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class staffRequestDto {

    private String staffCode;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String officeID;
    private String roleCode;
    private String phone;
    private int status;

}

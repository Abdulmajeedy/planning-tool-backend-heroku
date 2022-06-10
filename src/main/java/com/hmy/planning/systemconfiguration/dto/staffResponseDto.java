package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class staffResponseDto extends Auditable<String> {

    private String staffID;
    private String staffCode;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String officeID;
    private String phone;
    private int status;

}

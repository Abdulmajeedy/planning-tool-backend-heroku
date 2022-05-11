package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class userDetailsRequestDto extends Auditable<String> {

    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String officeCode;
    private int active;
    private String roles;

}

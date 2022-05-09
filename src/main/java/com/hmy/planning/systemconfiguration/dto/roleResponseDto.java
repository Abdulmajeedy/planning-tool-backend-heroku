package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

import java.time.Year;

@Data
public class roleResponseDto extends Auditable<String> {

    private String roleCode;
    private String role;
    private String description;
    private int status;
}

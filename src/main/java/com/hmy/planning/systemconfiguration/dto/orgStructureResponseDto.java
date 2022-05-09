package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class orgStructureResponseDto extends Auditable<String> {

    private String officeID;
    private String officeShortName;
    private String officeCode;
    private String officeName;
    private String reportTo;
    private int status;

}

package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class orgStructureRequestDto extends Auditable<String> {

    private String officeShortCode;
    private String officeName;
    private String officeCode;
    private String reportTo;
    private int status;

}

package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class targetResponseDto extends Auditable<String> {

    private String targetCode;
    private String targetName;
    private String strategyCode;
    private int status;

}

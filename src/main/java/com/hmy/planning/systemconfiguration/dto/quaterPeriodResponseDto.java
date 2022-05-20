package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class quaterPeriodResponseDto extends Auditable<String> {

    private String quaterPeriodCode;
    private String quaterName;
    private String alternativeName;
    private int status;

}

package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class budgetResponseDto extends Auditable<String> {

    private String budgetCode;
    private String year;
    private int status;
    private int isCurrent;
    private String activityCode;

}

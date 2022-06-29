package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class requestBudgetResponseDto extends Auditable<String> {

    private String requestBudgetCode;
    private String requestedDate;
    private String officeID;
    private String activityCode;
    private int requestedStatus;
    private int status;

}

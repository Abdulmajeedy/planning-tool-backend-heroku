package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class requestBudgetRequestDto {

    private String requestedDate;
    private String officeID;
    private String activityCode;
    private int requestedStatus;
    private int status;

}

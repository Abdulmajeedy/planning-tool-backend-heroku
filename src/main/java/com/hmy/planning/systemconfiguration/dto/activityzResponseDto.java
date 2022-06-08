package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class activityzResponseDto extends Auditable<String> {

    private String activityCode;
    private String activityName;
    private String targetCode;
    private String budgetYearCode;
    private String quaterPeriodCode;
    private int approvedStatus;
    private String officeID;
    private int Status;

}

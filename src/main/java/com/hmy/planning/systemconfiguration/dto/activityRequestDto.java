package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class activityRequestDto {

    private String activityName;
    private String targetCode;
    private String quaterPeriodCode;
    private String budgetYearCode;
    private String officeID;
    private int approvalStatus;
    private int Status;
    private int editStatus;

}

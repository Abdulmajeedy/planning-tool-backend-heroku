package com.hmy.planning.systemconfiguration.dto;

import java.util.UUID;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class activityResponseDto extends Auditable<String> {

    private String activityCode;
    private String activityName;
    private String targetCode;
    private String quaterPeriodCode;
    private String budgetYearCode;
    private String officeID;
    private int editStatus;
    private int approvalStatus;
    private int Status;

}

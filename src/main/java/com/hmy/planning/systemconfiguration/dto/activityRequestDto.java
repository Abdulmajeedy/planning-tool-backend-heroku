package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class activityRequestDto {

    private String activityName;
    private String targetCode;
    private String quaterPeriodCode;
    private String officeID;
    private int Status;

}

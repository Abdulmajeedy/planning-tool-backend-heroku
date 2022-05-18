package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class subactivityRequestDto {

    private String subactivityName;
    private int gfsCode;
    private int Unit_cost_per_unit;
    private int no_of_unit;
    private int estimates;
    private String activityCode;
    private int approvalStatus;
    private int status;

}

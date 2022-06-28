package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class subactivityResponseDto extends Auditable<String> {

    private String subactivityCode;
    private String subactivityName;
    private int gfsCode;
    private int Unit_cost_per_unit;
    private int no_of_unit;
    private String activityCode;
    private int estimates;
    private int approvalStatus;
    private int status;

}

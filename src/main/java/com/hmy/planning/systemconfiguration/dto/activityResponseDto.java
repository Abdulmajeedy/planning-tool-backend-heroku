package com.hmy.planning.systemconfiguration.dto;

import java.util.UUID;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class activityResponseDto extends Auditable<String> {

    private String activityCode;
    private String activityName;
    private String strategyCode;
    private String quaterPeriodCode;
    private int Status;

}

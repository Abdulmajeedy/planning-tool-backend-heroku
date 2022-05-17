package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class activityResponseDto extends Auditable<String> {

    private String activityCode;
    private String activityName;
    private String strategyCode;
    private int Status;

}

package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class activityQuaterPeriodResponseDto extends Auditable<String> {

    private String activityQuaterPeriodCode;
    private String QuaterPeriodCode;
    private String activityCode;
    private int status;

}

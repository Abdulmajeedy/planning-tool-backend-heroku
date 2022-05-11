package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class budgetPeriodRequestDto {

    private String year;
    private int status;

}

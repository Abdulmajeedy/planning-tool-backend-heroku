package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class impPeriodResponseDto extends Auditable<String> {

    private String impPeriodCode;
    private String budgetYearCode;
    private String quaterPeriodCode;
    private String startDate;
    private String endDate;
    private int status;
    private int isCurrent;

}

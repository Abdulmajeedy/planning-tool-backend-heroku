package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;

@Data
public class impPeriodRequestDto {

    private String budgetYearCode;
    private String quaterPeriodCode;
    private String startDate;
    private String endDate;
    private int status;
    private int isCurrent;

}

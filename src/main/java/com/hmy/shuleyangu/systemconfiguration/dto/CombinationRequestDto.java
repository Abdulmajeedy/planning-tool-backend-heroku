package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

@Data
public class CombinationRequestDto {
    private String combinationName;
    private String description;
    private String nectaCode;
    private Integer status;
    private String educationLevelId;
}

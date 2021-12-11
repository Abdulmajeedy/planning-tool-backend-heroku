package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

@Data
public class ClassRequestDto {
    private String className;
    private  String alternativeName;
    private Integer status;
    private String educationLevelId;
}

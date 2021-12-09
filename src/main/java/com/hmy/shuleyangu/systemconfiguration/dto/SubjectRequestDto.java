package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

@Data
public class SubjectRequestDto {
    private Integer nectaCode;
    private Integer emisCode;
    private String subjectName;
    private String subjectSchema;
    private String shortCode;
    private String subjectRegYear;
    private Integer status;
    private String educationLevelId;
}

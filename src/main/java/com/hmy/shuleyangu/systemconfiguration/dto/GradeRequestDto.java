package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

@Data
public class GradeRequestDto {
    private Integer gradePoint;
    private String gradeName;
    private Integer gradeMaxMark;
    private  Integer gradeMinMark;
    private Integer status;
    private String academicYearId;
    private String educationLevelId;
    private String remarkId;
}

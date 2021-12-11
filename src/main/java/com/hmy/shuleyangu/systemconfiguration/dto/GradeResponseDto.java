package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class GradeResponseDto extends Auditable<String> {
    private String gradeId;
    private String gradeName;
    private Integer gradePoint;
    private Integer gradeMaxMark;
    private  Integer gradeMinMark;
    private Integer status;
    private String academicYearId;
    private String educationLevelId;
    private String remarkId;

}

package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class SubjectResponseDto extends Auditable<String> {
    private String subjectId;
    private Integer nectaCode;
    private Integer emisCode;
    private String subjectName;
    private String subjectSchema;
    private String shortCode;
    private String subjectRegYear;
    private Integer status;
    private String educationLevelId;
}

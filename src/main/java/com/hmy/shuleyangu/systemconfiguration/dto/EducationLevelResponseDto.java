package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class EducationLevelResponseDto extends Auditable<String> {
    private String educationLevelId;
    private String educationLevelName;
    private String description;
    private Integer status;
}

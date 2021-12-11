package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class ClassResponseDto extends Auditable<String> {
    private String classId;
    private String className;
    private  String alternativeName;
    private Integer status;
    private String educationLevelId;
}

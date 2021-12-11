package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class CombinationResponseDto extends Auditable<String> {
    private String combinationId;
    private String combinationName;
    private String description;
    private String nectaCode;
    private Integer status;
    private String educationLevelId;
}

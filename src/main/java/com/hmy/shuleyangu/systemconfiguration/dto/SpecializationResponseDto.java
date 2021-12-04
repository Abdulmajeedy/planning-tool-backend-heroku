package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class SpecializationResponseDto extends Auditable<String> {
    private String specializationId;
    private String specializationName;
    private String specializationDescription;
    private Integer status;
}

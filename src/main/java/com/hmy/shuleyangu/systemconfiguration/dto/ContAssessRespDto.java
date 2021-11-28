package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class ContAssessRespDto extends Auditable<String> {
    private String contAssessmentTypeId;
    private String contAssessmentTypeName;
    private int status;
}

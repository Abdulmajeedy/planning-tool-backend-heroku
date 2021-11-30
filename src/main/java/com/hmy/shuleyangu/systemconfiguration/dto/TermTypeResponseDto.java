package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class TermTypeResponseDto extends Auditable<String> {
    private String termID;
    private String termTypeName;
    private Integer numberOfTerms;
    private Integer status;
}

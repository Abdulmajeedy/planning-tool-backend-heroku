package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

@Data
public class TermTypeRequestDto {
    private String termTypeName;
    private Integer numberOfTerms;
    private Integer status;
}

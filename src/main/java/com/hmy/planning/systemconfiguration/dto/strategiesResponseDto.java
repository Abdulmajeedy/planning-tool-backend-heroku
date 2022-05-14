package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class strategiesResponseDto extends Auditable<String> {

    private String strategyCode;
    private String strategy;
    private int status;

}

package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class objectiveResponseDto extends Auditable<String> {

    private String objectiveCode;
    private String objective;
    private int status;

}

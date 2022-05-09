package com.hmy.planning.systemconfiguration.dto;

import com.hmy.planning.systemconfiguration.models.Auditable;

import lombok.Data;

@Data
public class projectRequestDto extends Auditable<String> {

    private String projectName;
    private String description;
    private int status;

}

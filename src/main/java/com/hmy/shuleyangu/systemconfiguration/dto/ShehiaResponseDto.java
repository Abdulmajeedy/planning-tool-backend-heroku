package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

import java.util.UUID;

@Data
public class ShehiaResponseDto extends Auditable<String> {
    private UUID shehiaId;
    private String shehiaCode;
    private String shehiaName;
    private String districtName;
}

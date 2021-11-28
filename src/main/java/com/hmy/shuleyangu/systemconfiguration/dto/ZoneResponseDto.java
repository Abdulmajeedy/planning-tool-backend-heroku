package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

import java.util.UUID;

@Data
public class ZoneResponseDto extends Auditable<String> {
    private String zoneId;
    private String zoneCode;
    private String zoneName;
}

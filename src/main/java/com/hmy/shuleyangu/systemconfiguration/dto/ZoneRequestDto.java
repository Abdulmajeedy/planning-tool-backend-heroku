package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ZoneRequestDto {
    private String zoneCode;
    private String zoneName;
private UUID zoneId;
}

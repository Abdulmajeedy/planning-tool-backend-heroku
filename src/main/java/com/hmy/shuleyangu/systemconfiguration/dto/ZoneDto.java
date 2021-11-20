package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ZoneDto {
    private UUID zoneID;
    private String zoneCode;
    private String zoneName;
}

package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RegionRequestDto {
    private String regionCode;
    private String regionName;
    private UUID regionId;
//    private UUID zoneId;
}

package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import lombok.Data;

import java.util.UUID;

@Data
public class RegionRequestDto {
    private String regionCode;
    private String regionName;
    private UUID zoneId;

}

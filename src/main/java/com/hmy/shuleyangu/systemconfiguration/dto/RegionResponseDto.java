package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

import java.util.UUID;
@Data
public class RegionResponseDto extends Auditable<String> {
    private UUID regionId;
    private String regionCode;
    private String regionName;

}

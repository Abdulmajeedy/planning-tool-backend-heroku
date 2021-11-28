package com.hmy.shuleyangu.systemconfiguration.dto;
import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class RegionResponseDto extends Auditable<String> {
    private String regionId;
    private String regionCode;
    private String regionName;
    private String zoneId;

}

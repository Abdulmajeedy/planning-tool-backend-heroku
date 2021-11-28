package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;
;

@Data
public class DistrictResponseDto extends Auditable<String> {
    private String districtId;
    private String districtCode;
    private String districtName;
    private String regionId;
}

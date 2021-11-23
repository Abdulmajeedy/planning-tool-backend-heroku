package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

import java.util.UUID;

@Data
public class DistrictResponseDto extends Auditable<String> {
    private UUID districtId;
    private String districtCode;
    private String districtName;
}

package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class DistrictRequestDto {
    private UUID districtId;
    private String districtCode;
    private String districtName;
    private UUID regionId;


}
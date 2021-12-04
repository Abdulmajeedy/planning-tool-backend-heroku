package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class DisplineResponseDto extends Auditable<String> {
    private String displineId;
    private String remark;
    private Integer rate;
}

package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class ExtracariculumResponseDto extends Auditable<String> {
    private String curricularId;
    private String remark;
    private Integer rate;
}

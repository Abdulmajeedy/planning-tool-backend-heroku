package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class LatenessResponseDto extends Auditable<String> {
    private String latenessId;
    private String remark;
    private Integer rate;
}

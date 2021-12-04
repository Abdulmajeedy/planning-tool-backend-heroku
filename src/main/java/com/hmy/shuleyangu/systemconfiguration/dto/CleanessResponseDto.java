package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class CleanessResponseDto  extends Auditable<String > {
    private String CleanessId;
    private String remark;
    private Integer rate;
}

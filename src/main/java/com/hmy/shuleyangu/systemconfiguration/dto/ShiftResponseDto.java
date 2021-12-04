package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class ShiftResponseDto extends Auditable<String> {
    private String shiftId;
    private String shiftName;
    private Integer status;
}

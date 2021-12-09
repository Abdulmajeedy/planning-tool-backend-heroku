package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class RemarkResponseDto extends Auditable<String> {
    private String remarkId;
    private String remarkName;
}

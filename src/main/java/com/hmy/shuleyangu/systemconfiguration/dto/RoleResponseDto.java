package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

@Data
public class RoleResponseDto extends Auditable<String> {
    private String roleId;
    private String roleName;
    private Integer status;
}

package com.hmy.shuleyangu.systemconfiguration.dto;

import com.hmy.shuleyangu.systemconfiguration.models.Auditable;
import lombok.Data;

import java.time.Year;

@Data
public class AcademicYearResponseDto extends Auditable<String> {
    private String academicYearId;
    private String academicYearName;
    private int status;
}

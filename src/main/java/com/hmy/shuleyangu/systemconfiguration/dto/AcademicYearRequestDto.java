package com.hmy.shuleyangu.systemconfiguration.dto;

import lombok.Data;
import java.time.Year;

@Data
public class AcademicYearRequestDto {
    private Year academicYearName;
    private int status;

}

package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;
import java.util.UUID;
@Data
@Entity
public class AcademicYear extends Auditable<String>{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "academicYearId", updatable = false, nullable = false)
    private UUID academicYearId;
    private Year year;
    private int status;
}

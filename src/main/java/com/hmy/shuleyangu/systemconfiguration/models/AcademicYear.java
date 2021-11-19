package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class AcademicYear extends Auditable<String>{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "academicYearId", updatable = false, nullable = false)
    private UUID academicYearId;
    private Date year;
    private int status;

    public UUID getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(UUID academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

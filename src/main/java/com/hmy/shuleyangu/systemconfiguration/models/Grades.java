package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Grades extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String gradeId;
    private String gradeName;
    private Integer gradePoint;
    private Integer gradeMaxMark;
    private  Integer gradeMinMark;
    private Integer status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "remarkId",referencedColumnName = "remarkId")
    private Remarks remarks;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "academicYearId",referencedColumnName = "academicYearId")
    private AcademicYear academicYear;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "educationLevelId",referencedColumnName = "educationLevelId")
    private EducationLevel educationLevel;
}

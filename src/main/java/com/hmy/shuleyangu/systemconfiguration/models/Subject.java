package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Subject extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")  @Column(name = "regionId", updatable = false, nullable = false)
    private String subjectId;
    private Integer nectaCode;
    private Integer emisCode;
    private String subjectName;
    private String subjectSchema;
    private String shortCode;
    private String subjectRegYear;
    private Integer status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "educationLevel",referencedColumnName = "educationLevelId")
    private EducationLevel educationLevel;
}

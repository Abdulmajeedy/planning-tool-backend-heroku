package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class EducationLevel extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String educationLevelId;
    private String educationLevelName;
    private String description;
    private Integer status;

    @OneToMany(mappedBy = "educationLevel",fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "educationLevel",fetch = FetchType.LAZY)
    private List<Combination> subjectCombination;

    @OneToMany(mappedBy = "educationLevel",fetch = FetchType.LAZY)
    private List<Grades> grades;

    @OneToMany(mappedBy = "educationLevel",fetch = FetchType.LAZY)
    private List<Classes> classes;


}

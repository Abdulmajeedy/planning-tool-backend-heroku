package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Classes extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String classId;
    private String className;
    private  String alternativeName;
    private Integer status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "educationLevelId",referencedColumnName = "educationLevelId")
    private EducationLevel educationLevel;
}

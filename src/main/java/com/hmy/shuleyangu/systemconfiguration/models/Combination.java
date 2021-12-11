package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Combination extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "combinationId", updatable = false, nullable = false)
    private String combinationId;
    private String combinationName;
    private String description;
    private String nectaCode;
    private Integer status;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "educationLevel",referencedColumnName = "educationLevelId")
    private EducationLevel educationLevel;


}

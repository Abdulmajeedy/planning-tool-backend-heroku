package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
public class roles extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String roleCode;
    private String role;
    private int status;

    // @OneToMany(mappedBy = "academicYear", fetch = FetchType.LAZY)
    // private List<Grades> grades;
}

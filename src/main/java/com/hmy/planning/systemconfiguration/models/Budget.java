package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Entity
public class Budget extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String budgetCode;
    private String year;
    private int status;
    private int isCurrent;

    @ManyToOne
    @JoinColumn(name = "activityCode")
    private Activity activities;

}

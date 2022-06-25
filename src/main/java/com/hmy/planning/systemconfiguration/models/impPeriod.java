package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class impPeriod extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String impPeriodCode;
    private String startDate;
    private String endDate;
    private int status;
    private int isCurrent;

    @ManyToOne
    @JoinColumn(name = "quaterPeriodCode")
    private QuaterPeriod quaterPeriod;

    @ManyToOne
    @JoinColumn(name = "budgetYearCode")
    private budgetingPeriod budgetingPeriod;

    // @ManyToOne
    // @JoinColumn(name = "roleCode")
    // private Roles role;

}

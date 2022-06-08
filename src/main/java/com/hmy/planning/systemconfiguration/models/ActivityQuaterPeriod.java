package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class ActivityQuaterPeriod extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String activityQuaterPeriodCode;
    private int status;

    @ManyToOne
    @JoinColumn(name = "quaterPeriodCode")
    private QuaterPeriod quaterPeriod;

    @ManyToOne
    @JoinColumn(name = "activityCode")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "budgetYearCode")
    private budgetingPeriod budgetingPeriod;

}

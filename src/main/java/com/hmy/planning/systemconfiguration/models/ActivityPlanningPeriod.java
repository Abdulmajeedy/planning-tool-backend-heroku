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
public class ActivityPlanningPeriod extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String budgetCode;
    private String year;
    private int status;

    @ManyToOne
    @JoinColumn(name = "activityCode")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "budgetYearCode")
    private budgetingPeriod budgetingPeriod;

}

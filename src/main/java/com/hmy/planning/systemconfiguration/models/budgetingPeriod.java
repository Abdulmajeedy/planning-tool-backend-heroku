package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class budgetingPeriod extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String budgetYearCode;
    private String year;
    private int status;

    // @OneToMany(mappedBy = "budgetingPeriod", fetch = FetchType.LAZY)
    // private List<ActivityPlanningPeriod> activityPlanningPeriod;

    @OneToMany(mappedBy = "budgetingPeriod", fetch = FetchType.LAZY)
    private List<ActivityQuaterPeriod> activityQuaterPeriod;

    @OneToMany(mappedBy = "budgetingPeriod", fetch = FetchType.LAZY)
    private List<impPeriod> impPeriod;

    @OneToMany(mappedBy = "budgetingPeriod", fetch = FetchType.LAZY)
    private List<Objectives> objective;

}

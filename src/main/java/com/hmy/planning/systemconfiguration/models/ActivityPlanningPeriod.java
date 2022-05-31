package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class ActivityPlanningPeriod extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String ActivityPlanningCode;
    private String year;
    private int status;

    @ManyToOne
    @JoinColumn(name = "activityCode")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "budgetYearCode")
    private budgetingPeriod budgetingPeriod;

}

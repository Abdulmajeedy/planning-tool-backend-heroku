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
public class SubActivity extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String subactivityCode;
    private String subactivityName;
    private int gfsCode;
    private int Unit_cost_per_unit;
    private int no_of_unit;
    private int estimates;
    private int approvalStatus;
    private int status;

    @ManyToOne
    @JoinColumn(name = "activityCode")
    private Activity activity;

}

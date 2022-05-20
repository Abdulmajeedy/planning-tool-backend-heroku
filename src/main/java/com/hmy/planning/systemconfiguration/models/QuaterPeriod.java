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
public class QuaterPeriod extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String quaterPeriodCode;
    private String quaterName;
    private String alternativeName;
    private int Status;

    @OneToMany(mappedBy = "quaterPeriod", fetch = FetchType.LAZY)
    private List<ActivityQuaterPeriod> activityQuaterPeriod;

}

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
public class RequestBudget extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String requestBudgetCode;
    private String requestedDate;
    private int requestedStatus;
    private int status;

    @ManyToOne
    @JoinColumn(name = "activityCode")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "officeID")
    private orgStructure orgStructure;

}

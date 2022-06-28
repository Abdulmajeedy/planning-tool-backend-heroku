package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
public class Target extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String targetCode;

    @Column(length = 2048)
    private String targetName;
    private int status;

    @ManyToOne
    @JoinColumn(name = "strategyCode")
    private Strategies strategies;

    // @OneToMany(mappedBy = "target", fetch = FetchType.LAZY)
    // private List<Activity> activity;

}

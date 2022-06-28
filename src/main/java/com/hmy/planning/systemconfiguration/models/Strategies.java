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
import org.hibernate.type.TextType;

import lombok.Data;

@Data
@Entity
public class Strategies extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String strategyCode;
    private TextType strategy;
    private int status;

    // @ManyToOne
    // @JoinColumn(name = "objectiveCode")
    // private Objectives objectives;

    @ManyToOne
    @JoinColumn(name = "objectiveCode")
    private Objectives Objectives;

    @OneToMany(mappedBy = "strategies", fetch = FetchType.LAZY)
    private List<Target> target;

}

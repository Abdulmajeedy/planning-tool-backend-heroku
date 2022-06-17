package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class login extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String loginCode;
    private String email;
    private String password;
    private int logins;
    private int status;

    @OneToOne(mappedBy = "login")
    private staff staff;

    @OneToOne
    @JoinColumn(name = "roleCode")
    private Roles role;

    @OneToOne
    @JoinColumn(name = "officeID")
    private orgStructure orgStructure;

}

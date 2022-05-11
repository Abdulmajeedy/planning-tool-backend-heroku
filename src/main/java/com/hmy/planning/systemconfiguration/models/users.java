package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class users extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2364534L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String userCode;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String officeCode;
    private boolean active;
    private String roles;

}

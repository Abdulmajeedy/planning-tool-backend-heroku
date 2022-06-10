package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class staff extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String staffID;
    private String staffCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private int status;

    @OneToOne
    @JoinColumn(name = "loginCode")
    private login login;

}

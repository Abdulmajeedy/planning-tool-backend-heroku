package com.hmy.planning.systemconfiguration.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Roles extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String roleCode;
    private String role;
    private String description;
    private int status;

    // @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    // private List<login> login;
    // @OneToOne(mappedBy = "roles")
    // private login logn;

}

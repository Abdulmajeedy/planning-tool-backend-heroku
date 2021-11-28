package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class District extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")private String districtId;
    private String districtCode;
    private String districtName;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToMany(mappedBy = "district")
    private List<Shehia> shehia;
}


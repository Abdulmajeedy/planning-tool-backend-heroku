package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class District extends Auditable<String>{
    @Id
    @GeneratedValue
    private UUID districtId;
    private String districtCode;
    private String districtName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToMany(mappedBy = "district")
    private List<Shehia> shehia;


}

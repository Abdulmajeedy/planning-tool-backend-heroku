package com.hmy.shuleyangu.systemconfiguration.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Entity
public class District extends Auditable<String>{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID districtId;
    private String districtCode;
    private String districtName;
    @ManyToMany(targetEntity = Region.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "rg_fk",referencedColumnName = "regionId")
    private List<Region> regionId;


    public UUID getDistrictId() {
        return districtId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public District(UUID districtId, String districtCode, String districtName, String regionCode) {
        this.districtId = districtId;
        this.districtCode = districtCode;
        this.districtName = districtName;

    }
}

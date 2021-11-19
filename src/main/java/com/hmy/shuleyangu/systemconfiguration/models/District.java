package com.hmy.shuleyangu.systemconfiguration.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
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

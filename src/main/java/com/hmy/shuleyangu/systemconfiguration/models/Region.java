package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Region extends Auditable<String>{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID regionId;
    @Column(name="regionCode")
    private String regionCode;
    @Column(name="regionName")
    private String regionName;
//
//    @OneToMany(targetEntity = Zones.class,cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(referencedColumnName = "zoneID")
   private UUID zoneID;

    public UUID getRegionId() {
        return regionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }




}

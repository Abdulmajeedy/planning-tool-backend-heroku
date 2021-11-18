package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Zones extends Auditable<String>{
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID zoneID;
    private String zoneCode;
    private String zoneName;

//
//    @ManyToOne
//    @JoinColumn(name = "region_id_region_id")
    private UUID regionId;

    public void setZoneID(UUID zoneID) {
        this.zoneID = zoneID;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public UUID getZoneID() {
        return zoneID;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

}

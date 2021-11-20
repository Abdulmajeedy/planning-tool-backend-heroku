package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Zones extends Auditable<String>{
    @Id
    @Column(name = "zoneId", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID zoneId;
    private String zoneCode;
    private String zoneName;

    @OneToMany(mappedBy = "zones")
    private List<Region> region;

    public void setZoneID(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

}

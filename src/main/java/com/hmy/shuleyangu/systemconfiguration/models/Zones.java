package com.hmy.shuleyangu.systemconfiguration.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Table
@Entity
public class Zones extends Auditable<String>{
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private final UUID zoneID;
    private final String zoneCode;
    private final String zoneName;

    public Zones(UUID zoneID, String zoneCode, String zoneName) {
        this.zoneID = zoneID;
        this.zoneCode = zoneCode;
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

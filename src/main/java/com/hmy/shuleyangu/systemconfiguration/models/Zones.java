package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;
@Data
@AllArgsConstructor
@Table
@Entity
public class Zones extends Auditable<String>{
    @Id
    @SequenceGenerator(
            name = "zone_sequence",
            sequenceName = "zone_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "zone_sequence"

    )
    private final UUID zoneID;
    private final String zoneCode;
    private final String zoneName;


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

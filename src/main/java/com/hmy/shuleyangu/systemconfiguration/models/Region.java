package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Region extends Auditable<String>{
    @Id
    @Column(name = "regionId", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID regionId;
    @Column(name="regionCode")
    private String regionCode;
    @Column(name="regionName")
    private String regionName;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "zoneId")
   private Zones zones;


    @OneToMany(mappedBy = "region")
    private List<District> districts;

    public UUID getZoneId() {
        return zones.getZoneId();
    }
}

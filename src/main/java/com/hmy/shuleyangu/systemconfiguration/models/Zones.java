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
@Entity
public class Zones extends Auditable<String>{
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID zoneId;
    private String zoneCode;
    private String zoneName;

    @OneToMany(mappedBy = "zones")
    private List<Region> region;


}

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
  @Column(name = "zoneId", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    private String Id;
    private String zoneCode;
    private String zoneName;

    @OneToMany(mappedBy = "zones")
    private List<Region> region;


}

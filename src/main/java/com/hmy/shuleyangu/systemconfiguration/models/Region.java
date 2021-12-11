package com.hmy.shuleyangu.systemconfiguration.models;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Region extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")  @Column(name = "regionId", updatable = false, nullable = false)
    private String regionId;
    private String regionCode;
    private String regionName;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "zoneId",referencedColumnName = "zoneId")
    private Zones zones;
    @OneToMany(mappedBy = "region")
    private List<District> districts;


}

package com.hmy.shuleyangu.systemconfiguration.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Zones extends Auditable<String>  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String zoneId;
    private String zoneCode;
    private String zoneName;
    @OneToMany(mappedBy = "zones",fetch = FetchType.LAZY)
    private List<Region> region;
}

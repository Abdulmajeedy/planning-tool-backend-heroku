package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Shehia extends Auditable<String>{
    @GeneratedValue(generator = "UUID")
    @Id
    private UUID shehiaId;
    private String shehiaCode;
    private String shehiaName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "districtId")
    private District district;



}

package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class Shehia extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    private String shehiaId;
    private String shehiaCode;
    private String shehiaName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "districtId")
    private District district;



}

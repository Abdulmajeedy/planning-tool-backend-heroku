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
public class TermTypes extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "termTypeId", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String termID;
    private String termTypeName;
    private Integer numberOfTerms;
    private Integer status;


}

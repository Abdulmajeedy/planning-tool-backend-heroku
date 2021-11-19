package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table
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

    public UUID getShehiaId() {
        return shehiaId;
    }

    public void setShehiaId(UUID shehiaId) {
        this.shehiaId = shehiaId;
    }

    public String getShehiaCode() {
        return shehiaCode;
    }

    public void setShehiaode(String shehiaode) {
        this.shehiaCode = shehiaode;
    }

    public String getShehiaName() {
        return shehiaName;
    }

    public void setShehiaName(String shehiaName) {
        this.shehiaName = shehiaName;
    }

}

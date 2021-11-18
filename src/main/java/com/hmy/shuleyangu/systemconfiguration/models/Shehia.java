package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Shehia extends Auditable<String>{
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID shehiaId;
    private String shehiaCode;
    private String shehiaName;
    private UUID districtId;

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

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }
}

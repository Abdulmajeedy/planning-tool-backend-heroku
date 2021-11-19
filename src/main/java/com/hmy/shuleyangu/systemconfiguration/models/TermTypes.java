package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class TermTypes extends Auditable<String>{
    @Id
    @Column(name = "termTypeId", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID termID;
    private String termTypeName;
    private Integer numberOfTerms;
    private Integer status;

    public UUID getTermID() {
        return termID;
    }

    public void setTermID(UUID termID) {
        this.termID = termID;
    }

    public String getTermTypeName() {
        return termTypeName;
    }

    public void setTermTypeName(String termTypeName) {
        this.termTypeName = termTypeName;
    }

    public Integer getNumberOfTerms() {
        return numberOfTerms;
    }

    public void setNumberOfTerms(Integer numberOfTerms) {
        this.numberOfTerms = numberOfTerms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

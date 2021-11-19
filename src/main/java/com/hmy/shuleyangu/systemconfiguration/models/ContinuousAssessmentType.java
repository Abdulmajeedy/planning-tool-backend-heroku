package com.hmy.shuleyangu.systemconfiguration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class ContinuousAssessmentType extends Auditable<String>{
    @Id
    @GeneratedValue
    private UUID continuousAssessmentTypeId;
    private String continuousAssessmentTypeName;
    private Integer status;

    public UUID getContinuousAssessmentTypeId() {
        return continuousAssessmentTypeId;
    }

    public void setContinuousAssessmentTypeId(UUID continuousAssessmentTypeId) {
        this.continuousAssessmentTypeId = continuousAssessmentTypeId;
    }

    public String getContinuousAssessmentTypeName() {
        return continuousAssessmentTypeName;
    }

    public void setContinuousAssessmentTypeName(String continuousAssessmentTypeName) {
        this.continuousAssessmentTypeName = continuousAssessmentTypeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

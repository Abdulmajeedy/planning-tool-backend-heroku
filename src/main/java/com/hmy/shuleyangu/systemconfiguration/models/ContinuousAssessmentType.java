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
@Entity
public class ContinuousAssessmentType extends Auditable<String> {
    @Id
    @GeneratedValue
    private UUID continuousAssessmentTypeId;
    private String continuousAssessmentTypeName;
    private Integer status;
}



package com.hmy.planning.systemconfiguration.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable<U> {
    @CreatedBy
    @Column(updatable = false, nullable = true)
    protected U createdBy;
    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(updatable = false, nullable = true)
    protected Date createdDate;
    @LastModifiedBy
    protected U modifiedBy;
    @LastModifiedDate
    protected Date modifiedDate;

}

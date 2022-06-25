package com.hmy.planning.systemconfiguration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmy.planning.systemconfiguration.models.impPeriod;

@Repository
public interface implPeriodRepository extends JpaRepository<impPeriod, String> {

}

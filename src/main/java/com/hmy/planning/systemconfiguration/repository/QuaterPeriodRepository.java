package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.QuaterPeriod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuaterPeriodRepository extends JpaRepository<QuaterPeriod, String> {

}

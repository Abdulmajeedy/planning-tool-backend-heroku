package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Strategies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategiesRepository extends JpaRepository<Strategies, String> {

}

package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.ActivityQuaterPeriod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityQuaterPeriodRepository extends JpaRepository<ActivityQuaterPeriod, String> {

}

package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {

}

package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Objectives;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objectives, String> {

}

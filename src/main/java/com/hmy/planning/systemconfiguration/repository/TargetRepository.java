package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Target;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, String> {

}

package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.SubActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, String> {

}

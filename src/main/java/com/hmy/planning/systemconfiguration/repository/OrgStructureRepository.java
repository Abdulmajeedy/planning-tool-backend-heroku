package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.orgStructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgStructureRepository extends JpaRepository<orgStructure, String> {

}

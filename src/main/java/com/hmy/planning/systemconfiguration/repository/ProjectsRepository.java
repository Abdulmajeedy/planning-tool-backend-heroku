package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<projects, String> {

}

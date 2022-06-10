package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {

}

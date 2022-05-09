package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<roles, String> {

}

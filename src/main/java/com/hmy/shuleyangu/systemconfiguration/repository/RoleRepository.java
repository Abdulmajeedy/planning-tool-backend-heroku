package com.hmy.shuleyangu.systemconfiguration.repository;

import com.hmy.shuleyangu.systemconfiguration.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
}

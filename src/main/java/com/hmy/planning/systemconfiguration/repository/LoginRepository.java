package com.hmy.planning.systemconfiguration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmy.planning.systemconfiguration.models.login;

@Repository
public interface LoginRepository extends JpaRepository<login, String> {

}

package com.hmy.planning.systemconfiguration.repository;

import java.util.Optional;

import com.hmy.planning.systemconfiguration.models.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<users, String> {

    Optional<users> findByUsername(String username);

    Optional<users> findById(Integer id);

}

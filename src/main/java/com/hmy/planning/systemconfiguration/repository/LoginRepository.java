package com.hmy.planning.systemconfiguration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hmy.planning.systemconfiguration.models.login;

@Repository
public interface LoginRepository extends JpaRepository<login, String> {

    @Query(value = "select log.* from login log where log.email=?1 ", nativeQuery = true)
    Optional<login> findByEmail(String email);

}

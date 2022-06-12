package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<staff, String> {

    @Query(value = "select count(stf.*) from  staff stf", nativeQuery = true)
    Long CountUsers();

}

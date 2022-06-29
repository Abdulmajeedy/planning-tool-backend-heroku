package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Budget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {

    @Query(value = "select * from activity Act,budget Bgt where Act.activity_code=Bgt.activity_code AND Act.officeID =?1", nativeQuery = true)
    List<Budget> findBudgetByOffice(String officeID);

}

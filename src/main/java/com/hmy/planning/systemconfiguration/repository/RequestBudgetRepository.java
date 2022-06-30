package com.hmy.planning.systemconfiguration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hmy.planning.systemconfiguration.models.RequestBudget;

public interface RequestBudgetRepository extends JpaRepository<RequestBudget, String> {

    @Query(value = "select * from request_budget ReqBgt where ReqBgt.officeID =?1", nativeQuery = true)
    List<RequestBudget> findRequestBudgetByOffice(String officeID);

}

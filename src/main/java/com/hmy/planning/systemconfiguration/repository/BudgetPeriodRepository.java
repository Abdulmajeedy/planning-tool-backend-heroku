package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.budgetingPeriod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPeriodRepository extends JpaRepository<budgetingPeriod, String> {

}

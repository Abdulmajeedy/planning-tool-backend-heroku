package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {

}

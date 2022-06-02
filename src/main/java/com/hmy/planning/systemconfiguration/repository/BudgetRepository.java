package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, String> {

}

package com.hmy.planning.systemconfiguration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmy.planning.systemconfiguration.models.RequestBudget;

public interface RequestBudgetRepository extends JpaRepository<RequestBudget, String> {

}

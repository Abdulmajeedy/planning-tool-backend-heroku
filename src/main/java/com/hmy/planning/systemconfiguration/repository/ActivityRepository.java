package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    @Query(value = "select planAct.* from activity_planning_period planAct,activity Act where planAct.activity_code=Act.activity_code"
            + " and planAct.grade_code=?1 and planAct.budget_year_code=?2 ", nativeQuery = true)
    List<Activity> filterActivities(String OfficeCode, String BudgetYearCode);

}

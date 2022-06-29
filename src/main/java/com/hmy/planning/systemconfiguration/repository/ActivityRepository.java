package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    @Query(value = "select planAct.* from activity_planning_period planAct,activity Act where planAct.activity_code=Act.activity_code"
            + " and planAct.grade_code=?1 and planAct.budget_year_code=?2 ", nativeQuery = true)
    List<Activity> filterActivities(String OfficeCode, String BudgetYearCode);

    @Query(value = "select * from activity_quater_period planAct,activity Act where planAct.activity_code=Act.activity_code", nativeQuery = true)
    List<Activity> GetActivities();

    @Query(value = "select count(Act.*) from  activity Act", nativeQuery = true)
    Long CountActivities();

    @Query(value = "select count(Act.*), Act.officeID ofc from  activity Act ,org_structure ofc where Act.officeID=ofc.officeID group by Act.officeID", nativeQuery = true)
    List<Map<String, Object>> GraphActivityByOffice();

    @Query(value = "select * from activity_quater_period planAct,activity Act where planAct.activity_code=Act.activity_code AND Act.officeID =?1", nativeQuery = true)
    List<Activity> findActivitiesByOffice(String officeID);

    @Query(value = "update activity Act  SET Act.edit_statsu = 0 where  Act.activity_code =?1", nativeQuery = true)
    List<Activity> UpdateEditStatus(String activityCode);

}

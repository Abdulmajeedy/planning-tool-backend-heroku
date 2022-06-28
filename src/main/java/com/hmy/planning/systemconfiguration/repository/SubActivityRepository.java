package com.hmy.planning.systemconfiguration.repository;

import com.hmy.planning.systemconfiguration.models.SubActivity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, String> {

    @Query(value = "select * from  sub_activity SubAct where SubAct.activity_code =?1", nativeQuery = true)
    List<SubActivity> findByActivityCode(String activityCode);

    // public List<SubActivity> findByActivityCode(String activityCode);

}

package com.hmy.shuleyangu.systemconfiguration.repository;

import com.hmy.shuleyangu.systemconfiguration.models.Lateness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatenessRepository extends JpaRepository<Lateness, String> {
}

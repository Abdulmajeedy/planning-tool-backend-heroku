package com.hmy.shuleyangu.systemconfiguration.repository;
import com.hmy.shuleyangu.systemconfiguration.models.Shehia;
import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShehiaRepository extends JpaRepository<Shehia, String>{
    Optional<Shehia> findById(String shehiaId);

}

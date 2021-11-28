package com.hmy.shuleyangu.systemconfiguration.repository;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zones, String> {
Optional<Zones>findById(String zoneId);
void deleteById(String zoneId);



}

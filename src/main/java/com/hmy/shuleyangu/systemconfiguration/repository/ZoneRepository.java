package com.hmy.shuleyangu.systemconfiguration.repository;

import com.hmy.shuleyangu.systemconfiguration.models.Zones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ZoneRepository extends CrudRepository<Zones,UUID> {
//List<Zones> findByName(String zoneName);
Optional<Zones>findById(UUID zoneId);

void deleteById(UUID zoneId);

//void updateById(UUID zoneId,Zones zone);

}

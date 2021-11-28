package com.hmy.shuleyangu.systemconfiguration.repository;

import com.hmy.shuleyangu.systemconfiguration.models.TermTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TermTypeRepository extends CrudRepository<TermTypes, String> {
}

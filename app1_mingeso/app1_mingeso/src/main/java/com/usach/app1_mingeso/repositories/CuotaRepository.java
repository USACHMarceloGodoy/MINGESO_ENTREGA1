package com.usach.app1_mingeso.repositories;

import com.usach.app1_mingeso.entities.CuotaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity, Long> {

}

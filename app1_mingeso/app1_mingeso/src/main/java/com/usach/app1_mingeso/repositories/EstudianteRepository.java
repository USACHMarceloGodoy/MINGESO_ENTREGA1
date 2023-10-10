package com.usach.app1_mingeso.repositories;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<EstudianteEntity, Long> {
    //Encontrar por rut
    EstudianteEntity findByRut(String rut);
    }

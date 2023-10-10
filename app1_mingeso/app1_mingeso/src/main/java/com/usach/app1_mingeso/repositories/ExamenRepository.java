package com.usach.app1_mingeso.repositories;

import com.usach.app1_mingeso.entities.ExamenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends JpaRepository<ExamenEntity, Long> {
    // Puedes agregar métodos específicos si necesitas consultas personalizadas para la entidad ExamenEntity
    @Query("SELECT e FROM ExamenEntity e WHERE e.numero_examen = :numeroExamen AND e.materia_examen = :materiaExamen")
    ExamenEntity findByNumeroExamenAndMateriaExamen(@Param("numeroExamen") int numeroExamen, @Param("materiaExamen") String materiaExamen);


}

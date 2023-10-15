package com.usach.app1_mingeso.repositories;

import com.usach.app1_mingeso.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    //Obtener promedio de examenes por rut
    @Query("SELECT AVG(n.puntaje) FROM NotaEntity n WHERE n.estudiante.rut = :rut")
    Double obtenerPromedioPorRut(@Param("rut") String rut);
    //Contar examenes rendidos por rut
    @Query("SELECT COUNT(n.puntaje) FROM NotaEntity n WHERE n.estudiante.rut = :rut")
    Integer contarExamenesRendidosPorRut(@Param("rut") String rut);
    //Obtener promedio de examenes por rut
    @Query("SELECT AVG(n.puntaje) FROM NotaEntity n WHERE n.estudiante.rut = :rut")
    Double obtenerPromedioPorRutYMateria(@Param("rut") String rut);
}

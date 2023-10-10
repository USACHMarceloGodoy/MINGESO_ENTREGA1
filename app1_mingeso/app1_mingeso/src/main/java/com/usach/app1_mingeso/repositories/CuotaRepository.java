package com.usach.app1_mingeso.repositories;

import com.usach.app1_mingeso.entities.CuotaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity, Long> {

    @Query("SELECT c FROM CuotaEntity c WHERE c.rutEstudiante = :rut")
    List<CuotaEntity> findByRut(@Param("rut") String rut);

    @Transactional
    @Modifying
    @Query("UPDATE CuotaEntity c SET c.pagado = CASE WHEN c.pagado = TRUE THEN FALSE ELSE TRUE END WHERE c.id = :idCuota")
    void actualizarEstadoDePago(@Param("idCuota") int idCuota);
}
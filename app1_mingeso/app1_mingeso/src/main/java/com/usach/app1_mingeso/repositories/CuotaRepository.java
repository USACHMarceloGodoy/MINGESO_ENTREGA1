package com.usach.app1_mingeso.repositories;

import com.usach.app1_mingeso.entities.CuotaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity, Long> {

    @Query("SELECT c FROM CuotaEntity c WHERE c.rutEstudiante = :rut")
    List<CuotaEntity> findByRut(@Param("rut") String rut);

    @Modifying
    @Query("UPDATE CuotaEntity c SET c.pagado = CASE WHEN c.pagado = TRUE THEN FALSE ELSE TRUE END WHERE c.id = :idCuota")
    void actualizarEstadoDePago(@Param("idCuota") long idCuota);

    @Query("SELECT c.monto FROM CuotaEntity c WHERE c.rutEstudiante = :rut")
    Integer obtenerPrecioCuota(@Param("rut") String rut);

    @Query("SELECT COUNT(c.id) FROM CuotaEntity c WHERE c.rutEstudiante = :rut")
    int contarCuotasPorRut(@Param("rut") String rut);

    @Query("SELECT COUNT(c.id) FROM CuotaEntity c WHERE c.rutEstudiante = :rut AND c.pagado = TRUE")
    int contarCuotasPagadasPorRut(@Param("rut") String rut);

    @Query("SELECT SUM(c.monto) FROM CuotaEntity c WHERE c.rutEstudiante = :rut AND c.pagado = TRUE")
    Integer montoPagadoPorRut(@Param("rut") String rut);

    @Query("SELECT MAX(c.fechaVencimiento) FROM CuotaEntity c WHERE c.rutEstudiante = :rut AND c.pagado = TRUE")
    Timestamp ultimoPagoPorRut(@Param("rut") String rut);

    @Query("SELECT SUM(c.monto) FROM CuotaEntity c WHERE c.rutEstudiante = :rut AND c.pagado = FALSE")
    Integer saldoRestantePorRut(@Param("rut") String rut);

    @Query("SELECT c.pagado FROM CuotaEntity c WHERE c.id = :idCuota")
    boolean obtenerEstadoDePago(@Param("idCuota") long idCuota);
}

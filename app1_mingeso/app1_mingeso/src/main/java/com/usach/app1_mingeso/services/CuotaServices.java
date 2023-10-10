package com.usach.app1_mingeso.services;

import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.repositories.CuotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuotaServices {

    @Autowired
    private CuotaRepository cuotaRepository;

    public CuotaEntity guardarCuota(CuotaEntity cuota) {
        return cuotaRepository.save(cuota);
    }

    public List<CuotaEntity> obtenerCuotas() {
        return (List<CuotaEntity>) cuotaRepository.findAll();
    }

    public List<CuotaEntity> obtenerCuotasPorRut(String rut) {
        return cuotaRepository.findByRut(rut);
    }

    @Transactional
    public void actualizarEstadoDePago(int idCuota) {
        cuotaRepository.actualizarEstadoDePago(idCuota);
    }
}

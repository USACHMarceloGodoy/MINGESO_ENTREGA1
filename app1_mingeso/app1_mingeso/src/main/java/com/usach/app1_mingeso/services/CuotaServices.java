package com.usach.app1_mingeso.services;

import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.repositories.CuotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    // Aplicar intereses a cuotas atrasadas
    public void aplicarIntereses() {
        List<CuotaEntity> cuotas = obtenerCuotas();
        Date fechaActual = new Date();

        for (CuotaEntity cuota : cuotas) {
            Date fechaVencimiento = cuota.getFechaVencimiento();
            int mesesDeAtraso = calcularMesesDeAtraso(fechaActual, fechaVencimiento);

            float monto = cuota.getMonto();
            float tasaDeInteres;

            if (!cuotaRepository.obtenerEstadoDePago(cuota.getId())){
                if (mesesDeAtraso == 0) {
                    tasaDeInteres = 0.0f;
                } else if (mesesDeAtraso == 1) {
                    tasaDeInteres = 0.03f;
                } else if (mesesDeAtraso == 2) {
                    tasaDeInteres = 0.06f;
                } else if (mesesDeAtraso == 3) {
                    tasaDeInteres = 0.09f;
                } else if (mesesDeAtraso > 3){
                    tasaDeInteres = 0.15f;
                } else {
                    tasaDeInteres = 0.0f;
                }
            }else{
                tasaDeInteres = 0.0f;
            }

            float interes = monto * tasaDeInteres;
            cuota.setMonto(monto + interes);
            guardarCuota(cuota);
        }
    }

    private int calcularMesesDeAtraso(Date fechaActual, Date fechaVencimiento) {
        Calendar calFechaActual = Calendar.getInstance();
        calFechaActual.setTime(fechaActual);

        Calendar calFechaVencimiento = Calendar.getInstance();
        calFechaVencimiento.setTime(fechaVencimiento);

        int diffYears = calFechaActual.get(Calendar.YEAR) - calFechaVencimiento.get(Calendar.YEAR);
        int diffMonths = diffYears * 12 + calFechaActual.get(Calendar.MONTH) - calFechaVencimiento.get(Calendar.MONTH);

        return diffMonths;
    }
}


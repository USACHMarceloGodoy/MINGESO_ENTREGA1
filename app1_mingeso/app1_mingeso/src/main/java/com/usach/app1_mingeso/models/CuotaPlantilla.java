package com.usach.app1_mingeso.models;

import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.repositories.CuotaRepository;
import com.usach.app1_mingeso.repositories.NotaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
public class CuotaPlantilla {

    private String rut;
    private String nombre;
    private int examenes_rendidos;
    private Double promedio;
    private Integer arancel;
    private String tipo_pago;
    private int numero_cuotas;
    private int cuotas_pagadas;
    private Integer monto_pagado;
    private LocalDate ultimo_pago;
    private Integer saldo_restante;
    private NotaRepository notaRepository;
    private CuotaRepository cuotaRepository;

    private LocalDate convertirTimestampALocalDate(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public CuotaPlantilla(EstudianteEntity estudiante, ArrayList<CuotaEntity> cuotas,
                          NotaRepository notaRepository, CuotaRepository cuotaRepository) {
        this.nombre = estudiante.getNombres();
        this.rut = estudiante.getRut();
        this.promedio = notaRepository.obtenerPromedioPorRut(rut);
        this.examenes_rendidos = notaRepository.contarExamenesRendidosPorRut(rut);
        this.arancel = cuotaRepository.obtenerPrecioCuota(rut);
        if (cuotaRepository.contarCuotasPorRut(rut) == 1) {
            this.tipo_pago = "Contado";
        } else {
            this.tipo_pago = "Cuotas";
        }
        this.numero_cuotas = cuotaRepository.contarCuotasPorRut(rut);
        this.cuotas_pagadas = cuotaRepository.contarCuotasPagadasPorRut(rut);
        this.monto_pagado = cuotaRepository.montoPagadoPorRut(rut);
        this.ultimo_pago = convertirTimestampALocalDate(cuotaRepository.ultimoPagoPorRut(rut));
        Integer saldoRestante = cuotaRepository.saldoRestantePorRut(rut);
        this.saldo_restante = (saldoRestante != null) ? saldoRestante : 0;
    }

    public String getRut() {
        return this.rut;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getExamenes_rendidos() {
        return this.examenes_rendidos;
    }

    public Double getPromedio() {
        return this.promedio;
    }

    public Integer getArancel() {
        return this.arancel;
    }

    public String getTipo_pago() {
        return this.tipo_pago;
    }

    public int getNumero_cuotas() {
        return this.numero_cuotas;
    }

    public int getCuotas_pagadas() {
        return this.cuotas_pagadas;
    }

    public Integer getMonto_pagado() {
        return this.monto_pagado;
    }

    public LocalDate getUltimo_pago() {
        return this.ultimo_pago;
    }

    public Integer getSaldo_restante() {
        return this.saldo_restante;
    }

    public NotaRepository getNotaRepository() {
        return this.notaRepository;
    }

    public CuotaRepository getCuotaRepository() {
        return this.cuotaRepository;
    }

}


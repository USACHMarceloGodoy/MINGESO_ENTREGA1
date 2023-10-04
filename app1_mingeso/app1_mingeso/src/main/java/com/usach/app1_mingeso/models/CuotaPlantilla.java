package com.usach.app1_mingeso.models;

import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
public class CuotaPlantilla {
    private EstudianteEntity estudiante;
    private ArrayList<CuotaEntity> cuotas;
    private String Rut;
    private String Nombre;
    private int examenes_rendidos;
    private int promedio;
    private int arancel;
    private String tipo_pago;
    private int numero_cuotas;
    private int cuota_pagadas;
    private int monto_pagado;
    private LocalDate ultimo_pago;
    private int saldo_restante;
    private int cuotas_retraso;
    public CuotaPlantilla(EstudianteEntity estudiante, ArrayList <CuotaEntity> cuotas){
        this.estudiante = estudiante;
        //Determino la fecha de hoy para ver cuantas cuotas estan vencidas
        LocalDate date = LocalDate.now();

    }
}

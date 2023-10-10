package com.usach.app1_mingeso.models;

import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.entities.NotaEntity;
import com.usach.app1_mingeso.repositories.CuotaRepository;
import com.usach.app1_mingeso.repositories.EstudianteRepository;
import com.usach.app1_mingeso.repositories.NotaRepository;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
public class CuotaPlantilla {
    private String rut;
    private String nombre;
    private int examenes_rendidos;
    private int promedio;
    //Esto es de la base de datos de la Cuota
    private int arancel;
    private String tipo_pago;
    private int numero_cuotas;
    private int cuota_pagadas;
    private int monto_pagado;
    private LocalDate ultimo_pago;
    private int saldo_restante;
    private int cuotas_retraso;
    public CuotaPlantilla(EstudianteEntity estudiante, ArrayList <CuotaEntity> cuotas){
        //Obtengo el nombre del estudiante
        this.nombre = estudiante.getNombres();
        //Obtengo el rut del estudiante
        this.rut = estudiante.getRut();
        //Obtengo el promedio del estudiante
        //this.promedio = obtenerPromedioPorRut(rut);
        //Examenes Rendidos
        //Obtengo el arancel del estudiante
        //Determino la fecha de hoy para ver cuantas cuotas estan vencidas
        LocalDate date = LocalDate.now();

    }
}

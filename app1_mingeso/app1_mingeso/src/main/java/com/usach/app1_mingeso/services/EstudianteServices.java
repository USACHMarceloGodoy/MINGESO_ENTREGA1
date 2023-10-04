package com.usach.app1_mingeso.services;

import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.repositories.EstudianteRepository;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class EstudianteServices {
    @Autowired
    EstudianteRepository estudianteRepository;
    public EstudianteEntity guardarEstudiante(EstudianteEntity estudiante){
        return estudianteRepository.save(estudiante);
    }
    public Iterable<EstudianteEntity> obtenerEstudiantes(){
        return estudianteRepository.findAll();
    }



    public double calcularCuotas(EstudianteEntity estudiante, int formaPago){
        Scanner scanner = new Scanner(System.in);
        double arancel = 1500000;
        double descuento = 0;
        //Descuento al contado
        if (formaPago == 1){
            return  (arancel * 0.5);
        }
        String tipo = estudiante.getTipo_colegio();
        //Descuento respecto al colegio de procedencia
        if (tipo == "Municipal"){
            descuento = 0.2;
        } else if (tipo == "Subvencionado") {
            descuento = 0.1;
        }
        //fecha actual
        LocalDate fechaActual = LocalDate.now();
        int anoActual = fechaActual.getYear();
        //descuento por fecha
        int anoSalida = estudiante.getAno_egreso();
        //descuento por año
        if (anoActual-anoSalida < 1){
            descuento = descuento + 0.15;
        } else if (anoActual-anoSalida <= 2) {
            descuento = descuento + 0.08;
        } else if (anoActual-anoSalida <= 4) {
            descuento = descuento + 0.04;
        }
        int maxCuotas = 4;
        if (tipo == "Municipal"){
            maxCuotas = 10;
        } else if (tipo == "Subvencionado") {
            maxCuotas = 7;
        }
        //preguntar cantidad de cuotas
        int cuotas = 0;
        if (cuotas < 1 || cuotas > maxCuotas){
            System.out.println("Ingrese un numero de cuotas valido");
            cuotas = scanner.nextInt();
        }
        return (arancel * (1-descuento))/cuotas;
    }
}

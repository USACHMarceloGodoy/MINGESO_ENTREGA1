package com.usach.app1_mingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuota", unique = true, nullable = false)
    private int id;

    private float monto;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "numero_cuota")
    private int numeroCuota;

    private boolean pagado;

    @Column(name = "rut_estudiante")
    private String rutEstudiante;
}

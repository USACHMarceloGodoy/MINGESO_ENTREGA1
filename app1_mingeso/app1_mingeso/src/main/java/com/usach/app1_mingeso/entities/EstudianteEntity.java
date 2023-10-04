package com.usach.app1_mingeso.entities;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private String rut;
    private String nombres;
    private String apellidos;
    private String tipo_colegio;
    private String nombre_colegio;
    private int ano_egreso;
    private LocalDate fecha_nacimiento;
}

package com.usach.app1_mingeso.entities;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_cuota;
    private float monto;
    private LocalDate fecha_inicio;
    private LocalDate fecha_vencimiento;
    private int numero_cuota;
    private boolean pagado;
    private String rut_estudiante;
}
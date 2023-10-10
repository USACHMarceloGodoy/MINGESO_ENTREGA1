package com.usach.app1_mingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "examenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_examen;

    @Column(nullable = false)
    private int numero_examen;

    @Column(nullable = false)
    private String materia_examen;

    // Puedes agregar más atributos según tus necesidades

}

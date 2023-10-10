package com.usach.app1_mingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_nota;

    @Column(nullable = false)
    private int puntaje;

    @Column(nullable = false)
    private boolean rendido;

    @ManyToOne
    @JoinColumn(name = "id_examen", nullable = false)
    private ExamenEntity examen;

    @ManyToOne
    @JoinColumn(name = "rut_estudiante", nullable = false)
    private EstudianteEntity estudiante;
}

// Puedes agregar más atributos según tus necesidades
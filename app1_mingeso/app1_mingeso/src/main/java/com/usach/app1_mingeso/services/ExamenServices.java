package com.usach.app1_mingeso.services;

import com.usach.app1_mingeso.entities.ExamenEntity;
import com.usach.app1_mingeso.repositories.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExamenServices {

    @Autowired
    private ExamenRepository examenRepository;

    public ExamenEntity guardarExamen(ExamenEntity examen) {
        return examenRepository.save(examen);
    }

    public ArrayList<ExamenEntity> obtenerExamenes() {
        return (ArrayList<ExamenEntity>) examenRepository.findAll();
    }

    // Puedes agregar métodos adicionales según tus necesidades

}

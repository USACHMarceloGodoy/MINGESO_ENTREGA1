package com.usach.app1_mingeso.services;

import com.usach.app1_mingeso.entities.ExamenEntity;
import com.usach.app1_mingeso.entities.NotaEntity;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.repositories.NotaRepository;
import com.usach.app1_mingeso.repositories.ExamenRepository;
import com.usach.app1_mingeso.repositories.EstudianteRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class NotaServices {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ExamenRepository examenRepository;

    public NotaEntity guardarNota(NotaEntity nota) {
        return notaRepository.save(nota);
    }

    public EstudianteEntity findByRut(String rut) {
        return estudianteRepository.findByRut(rut);
    }

    public ExamenEntity findByNumeroExamenAndMateriaExamen(int numeroExamen, String materiaExamen) {
        return examenRepository.findByNumeroExamenAndMateriaExamen(numeroExamen, materiaExamen);
    }

    public void importarNotasDesdeCSV(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(reader);

        for (CSVRecord csvRecord : csvParser) {
            // Lee los valores del CSV
            int numeroExamen = Integer.parseInt(csvRecord.get("n examen"));
            String materiaExamen = csvRecord.get("materia examen");
            int puntaje = Integer.parseInt(csvRecord.get("puntaje"));
            String rutEstudiante = csvRecord.get("rut_estudiante");

            // Busca el estudiante y el examen correspondientes
            EstudianteEntity estudiante = findByRut(rutEstudiante);
            ExamenEntity examen = findByNumeroExamenAndMateriaExamen(numeroExamen, materiaExamen);

            // Crea la entidad NotaEntity y guárdala
            NotaEntity nota = new NotaEntity();
            nota.setPuntaje(puntaje);
            nota.setRendido(true);
            nota.setEstudiante(estudiante);
            nota.setExamen(examen);

            // Guarda la entidad NotaEntity en la base de datos
            guardarNota(nota);
        }
    }
}
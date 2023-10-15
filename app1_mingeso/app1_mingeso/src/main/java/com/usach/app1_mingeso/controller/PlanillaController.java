package com.usach.app1_mingeso.controller;

import org.springframework.ui.Model;
import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.models.CuotaPlantilla;
import com.usach.app1_mingeso.repositories.CuotaRepository;
import com.usach.app1_mingeso.repositories.NotaRepository;
import com.usach.app1_mingeso.services.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
@Controller
@RequestMapping("/cuota-plantilla")
public class PlanillaController {

    @Autowired
    private EstudianteServices estudianteServices;

    @Autowired
    private CuotaRepository cuotaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping("/generar")
    public String mostrarCuotaPlanilla(@RequestParam String rut, Model model) {
        try {
            if (rut == null || rut.isEmpty()) {
                // Manejar el caso en que el parámetro sea nulo o vacío
                model.addAttribute("error", "El parámetro 'rut' no puede estar vacío");
                return "error";  // Puedes tener una página de error específica
            }

            EstudianteEntity estudiante = estudianteServices.obtenerPorRut(rut);
            if (estudiante == null) {
                // Manejar el caso en que no se encuentre un estudiante con el rut proporcionado
                model.addAttribute("error", "No se encontró un estudiante con el rut proporcionado");
                return "error";  // Puedes tener una página de error específica
            }

            ArrayList<CuotaEntity> cuotas = (ArrayList<CuotaEntity>) cuotaRepository.findByRut(rut);
            CuotaPlantilla cuotaPlantilla = new CuotaPlantilla(estudiante, cuotas, notaRepository, cuotaRepository);

            // Agregar el objeto cuotaPlantilla al modelo
            model.addAttribute("cuotaPlantilla", cuotaPlantilla);

            return "cuota-plantilla";
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones
            model.addAttribute("error", "Se produjo un error al procesar la solicitud");
            return "error";  // Puedes tener una página de error específica
        }
    }
}


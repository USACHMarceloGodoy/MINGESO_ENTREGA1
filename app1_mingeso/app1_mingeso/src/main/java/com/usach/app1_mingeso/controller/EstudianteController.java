package com.usach.app1_mingeso.controller;

import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.services.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    EstudianteServices estudianteServices;

    @GetMapping
    public String list(Model model) {
        Iterable<EstudianteEntity> estudiantes = estudianteServices.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes";
    }

    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new EstudianteEntity());
        return "agregarEstudiante";
    }

    @PostMapping("/agregar")
    public String agregarEstudiante(@ModelAttribute("estudiante") EstudianteEntity estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "agregarEstudiante";
        }
        estudianteServices.guardarEstudiante(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/calcular-cuotas")
    public String mostrarPaginaCalcularCuotas(Model model) {
        // Create a new EstudianteEntity to bind the form
        model.addAttribute("estudiante", new EstudianteEntity());
        return "calcularCuotas";
    }

    @PostMapping("/calcular-cuotas")
    public String calcularCuotas(@ModelAttribute("estudiante") EstudianteEntity estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "calcularCuotas";
        }
        // Handle calculation logic here
        return "redirect:/estudiantes";
    }
    //obtener planilla de cuotas
    @GetMapping("/planilla/{rut}")
    public String getPlanillaEstudiante(@PathVariable("rut") String rut, Model model) {
        EstudianteEntity estudiante = estudianteServices.obtenerPorRut(rut);

        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
            return "planilla";
        }
        return "redirect:/estudiantes";
    }
}


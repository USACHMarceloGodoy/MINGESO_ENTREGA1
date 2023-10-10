package com.usach.app1_mingeso.controller;

import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.services.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class EstudianteController {

    @Autowired
    EstudianteServices estudianteServices;

    @GetMapping("/estudiantes")
    public String list(Model model) {
        Iterable<EstudianteEntity> estudiantes = estudianteServices.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes";
    }

    @GetMapping("/estudiantes/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new EstudianteEntity());
        return "agregarEstudiante";
    }

    @PostMapping("/estudiantes/agregar")
    public String agregarEstudiante(@ModelAttribute("estudiante") EstudianteEntity estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "agregarEstudiante";
        }
        estudianteServices.guardarEstudiante(estudiante);
        return "redirect:/estudiantes";
    }

    @PostMapping("/estudiantes/calcular-cuotas")
    public String calcularCuotas(@ModelAttribute("estudiante") EstudianteEntity estudiante,
                                 @RequestParam int formaPago, Model model) {
        double cuotaCalculada = estudianteServices.calcularCuotas(estudiante, formaPago);
        model.addAttribute("cuotaCalculada", cuotaCalculada);
        return "resultadoCuotas";
    }
}

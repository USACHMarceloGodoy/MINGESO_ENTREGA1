package com.usach.app1_mingeso.controller;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import org.springframework.ui.Model;
import com.usach.app1_mingeso.services.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        model.addAttribute("estudiantes", new EstudianteEntity());
        return "agregarEstudiante";
    }
    @PostMapping("/estudiante")
    public String agregarEstudiante(@ModelAttribute EstudianteEntity estudiantes, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "agregarEstudiante";
        }
        model.addAttribute("estudiantes", estudiantes);
        estudianteServices.guardarEstudiante(estudiantes);
        return "redirect:/listar";
    }
    @PostMapping("estudiante/calcular-cuotas")
    public String calcularCuotas(@ModelAttribute EstudianteEntity estudiante, int formaPago, Model model) {
        double cuotaCalculada = estudianteServices.calcularCuotas(estudiante, formaPago);
        model.addAttribute("cuotaCalculada", cuotaCalculada);
        return "resultadoCuotas";
    }
}

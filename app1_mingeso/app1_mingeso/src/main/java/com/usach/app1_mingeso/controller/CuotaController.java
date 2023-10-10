package com.usach.app1_mingeso.controller;

import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.services.CuotaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CuotaController {

    @Autowired
    private CuotaServices cuotaServices;

    @GetMapping("/mostrarCuotasPorRut")
    public String mostrarCuotasPorRut() {
        return "mostrarCuotasPorRut";
    }
    @ResponseBody
    @GetMapping("/obtenerCuotasPorRut")
    public List<CuotaEntity> obtenerCuotasPorRut(@RequestParam(value = "rut") String rut) {
        return cuotaServices.obtenerCuotasPorRut(rut);
    }

    @PostMapping("/actualizarEstadoDePago/{idCuota}")
    public void actualizarEstadoDePago(@PathVariable int idCuota) {
        cuotaServices.actualizarEstadoDePago(idCuota);
    }
}


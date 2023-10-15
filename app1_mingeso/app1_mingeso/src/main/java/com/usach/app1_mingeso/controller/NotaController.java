package com.usach.app1_mingeso.controller;

import com.usach.app1_mingeso.services.NotaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class NotaController {

    @Autowired
    private NotaServices notaServices;

    @PostMapping("/importar-csv")
    public ResponseEntity<String> importarNotasDesdeCSV(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("El archivo está vacío.", HttpStatus.BAD_REQUEST);
            }

            InputStream inputStream = file.getInputStream();
            notaServices.importarNotasDesdeCSV(inputStream);

            return new ResponseEntity<>("Notas importadas correctamente.", HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
            return new ResponseEntity<>("Error al importar las notas desde el archivo CSV.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/importarNotas")
    public String mostrarPaginaImportarNotas() {
        return "importarNotas"; // Nombre del archivo HTML sin extensión
    }
}
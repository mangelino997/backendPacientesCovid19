package com.pacientesCovid19.restful.controller;

import com.pacientesCovid19.restful.model.ColorDiagnostic;
import com.pacientesCovid19.restful.service.ColorDiagnosticService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcio
 */
@RestController
@RequestMapping("api/colordiagnostic")
public class ColorDiagnosticController {
    
    @Autowired
    ColorDiagnosticService elementoService;
    
    @GetMapping("")
    public List<ColorDiagnostic> listar(){
        return elementoService.listar();
    }
    
    @PostMapping(value = "")
    public ResponseEntity<?> agregar(@RequestBody ColorDiagnostic cd) {
        try {
            ColorDiagnostic colorDiagnostic = elementoService.agregar(cd);
            return ResponseEntity.status(HttpStatus.CREATED).body(colorDiagnostic);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(value = "")
    public ColorDiagnostic actualizar(@RequestBody ColorDiagnostic cd) {
        return elementoService.actualizar(cd);
    }
}

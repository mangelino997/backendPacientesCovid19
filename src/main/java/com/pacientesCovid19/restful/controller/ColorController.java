package com.pacientesCovid19.restful.controller;

import com.pacientesCovid19.restful.model.Color;
import com.pacientesCovid19.restful.service.ColorService;
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
@RequestMapping("api/")
public class ColorController {

    @Autowired
    ColorService elementoService;

    @GetMapping("colores")
    public List<Color> listar() {
        return elementoService.listar();
    }

    @PostMapping(value = "colores")
    public ResponseEntity<?> agregar(@RequestBody Color cd) {
        try {
            Color color = elementoService.agregar(cd);
            return ResponseEntity.status(HttpStatus.CREATED).body(color);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(value = "colores")
    public ResponseEntity<?> actualizar(@RequestBody Color cd) {
        try {
            Color color = elementoService.actualizar(cd);
            return ResponseEntity.status(HttpStatus.OK).body(color);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}

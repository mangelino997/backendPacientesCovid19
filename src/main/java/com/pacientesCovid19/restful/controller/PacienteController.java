package com.pacientesCovid19.restful.controller;

import com.pacientesCovid19.restful.dto.PacienteDTO;
import com.pacientesCovid19.restful.model.Color;
import com.pacientesCovid19.restful.model.Paciente;
import com.pacientesCovid19.restful.service.PacienteService;
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
@RequestMapping("api/pacientes")
public class PacienteController {

    @Autowired
    PacienteService elementoService;

    @GetMapping("")
    public List<Paciente> listar() {
        return elementoService.listar();
    }

    @PostMapping(value = "")
    public ResponseEntity<?> agregar(@RequestBody PacienteDTO pacienteNuevo) {
        try {
            Paciente paciente = elementoService.agregar(pacienteNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> actualizar(@RequestBody Paciente pacienteEditado) {
        try {
            Paciente paciente = elementoService.actualizar(pacienteEditado);
            return ResponseEntity.status(HttpStatus.OK).body(paciente);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}

package com.pacientesCovid19.restful.controller;

import com.pacientesCovid19.restful.dto.FilasPacientesDTO;
import com.pacientesCovid19.restful.dto.FiltroDTO;
import com.pacientesCovid19.restful.dto.PacienteDTO;
import com.pacientesCovid19.restful.model.Paciente;
import com.pacientesCovid19.restful.service.PacienteService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcio
 */
@RestController
@RequestMapping("api/")
public class PacienteController {

    @Autowired
    PacienteService elementoService;

    @GetMapping("pacientes")
    public FilasPacientesDTO listar() {
        return elementoService.listar();
    }

    @PostMapping(value = "pacientes")
    public ResponseEntity<?> agregar(@RequestBody PacienteDTO pacienteNuevo) {
        try {
            Paciente paciente = elementoService.agregar(pacienteNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping(value = "pacientes/{id}/status")
    public ResponseEntity<?> actualizarEstado(@PathVariable long id) {
        try {
            Paciente paciente = elementoService.obtenerPorId(id);
            elementoService.actualizarEstadoAtendido(paciente);
            return ResponseEntity.status(HttpStatus.OK).body(paciente);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
    //Obtiene las filas de pacientes por filtro y color
    @PostMapping("pacientes/listarPorFiltros")
    public FilasPacientesDTO listarPorFiltros(@RequestBody FiltroDTO filtroDTO) throws IOException {
        return elementoService.listarPorFiltros(filtroDTO);
    }

}

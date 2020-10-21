package com.pacientesCovid19.restful.service;

import com.pacientesCovid19.restful.dto.PacienteDTO;
import com.pacientesCovid19.restful.model.Color;
import com.pacientesCovid19.restful.model.Paciente;
import com.pacientesCovid19.restful.repository.IColorImpl;
import com.pacientesCovid19.restful.repository.IPacienteImpl;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marcio
 */
@Service
public class PacienteService {

    @Autowired
    IPacienteImpl pacienteRepository;

    @Autowired
    IColorImpl colorRepository;

    @Transactional(readOnly = true) // operation read only
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente agregar(PacienteDTO nuevoPaciente) {

        int valor_mareo = nuevoPaciente.getMareo().equalsIgnoreCase("Si") ? 100 : 0;
        int valor_edad = medirEdad(nuevoPaciente.getEdad());
        int valor_presion = medirPresion(nuevoPaciente.getPresion());
        int valor_fiebre = medirFiebre(nuevoPaciente.getFiebre());
        int valor_hipertension = nuevoPaciente.getHipertension().equalsIgnoreCase("Si") ? 100 : 0;
        int puntaje = valor_mareo + valor_edad + valor_presion + valor_fiebre + valor_hipertension;

        Date date = new Date(new java.util.Date().getTime());
        Color color = colorRepository.getColorBetweenRange(puntaje);

        Paciente paciente = new Paciente();
        paciente.setName(nuevoPaciente.getNombre());
        paciente.setDni(nuevoPaciente.getDni());
        paciente.setColor(color);
        paciente.setScore(puntaje);
        paciente.setStatus(asignarStatus(color) < color.getCant_max_patient()? "siendo atendido": "en espera");
        paciente.setDate(date);
        Paciente p = pacienteRepository.saveAndFlush(paciente);
        return p;
    }
    
    private int asignarStatus(Color color){
        int cantidadSiendoAtendidos = pacienteRepository.cantidadPacientesSiendoAtendidos(color.getId());
        return cantidadSiendoAtendidos;
    }

    public int medirEdad(int edad) {
        if (edad < 6) {
            return 100;
        } else if (6 <= edad && edad < 12) {
            return 50;
        } else if (12 <= edad && edad < 20) {
            return 20;
        } else if (20 <= edad && edad < 40) {
            return 15;
        } else if (40 <= edad && edad < 50) {
            return 25;
        } else if (50 <= edad && edad < 65) {
            return 40;
        } else {
            return 100;
        }
    }

    public int medirPresion(int presion) {
        if (presion < 13) {
            return 30;
        } else if (13 <= presion && presion < 14) {
            return 0;
        } else {
            return 100;
        }
    }

    public int medirFiebre(double fiebre) {
        if (fiebre < 37.5) {
            return 0;
        } else if (37.5 <= fiebre && fiebre < 38) {
            return 0;
        } else if (38 <= fiebre && fiebre < 40) {
            return 70;
        } else {
            return 100;
        }
    }

    /*private gestionFilas(Color color, List<Paciente> l){
        
    }
     */
    public Paciente actualizar(Paciente paciente) {
        Paciente p = pacienteRepository.save(paciente);
        return p;
    }

}

package com.pacientesCovid19.restful.service;

import com.pacientesCovid19.restful.dto.FilasPacientesDTO;
import com.pacientesCovid19.restful.dto.FiltroDTO;
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

    FilasPacientesDTO filas = new FilasPacientesDTO();

    //Operacion de solo lectura. Devuelve las listas para cada color
    @Transactional(readOnly = true)
    public FilasPacientesDTO listar() {
        List<Color> lc = colorRepository.findAll();
        lc.forEach(color -> {
            List<Paciente> listaPorColor = pacienteRepository.findByColorIdOrderByDateAsc(color.getId());
            seleccionarFila(listaPorColor, color.getColor());
        });
        return filas;
    }

    //Asigna cada lista por color
    private void seleccionarFila(List<Paciente> lista, String color) {
        switch (color) {
            case "Verde":
                filas.setFilaVerde(lista);
                break;
            case "Amarillo":
                filas.setFilaAmarillo(lista);
                break;
            case "Naranja":
                filas.setFilaNaranja(lista);
                break;
            case "Rojo":
                filas.setFilaRojo(lista);
                break;
            default:
                return;
        }
    }

    //Evalua al paciente. De tipo DTO porque los datos que ingresan son distintos a los datos del modelo.
    public Paciente agregar(PacienteDTO nuevoPaciente) {

        int valor_hipertension = nuevoPaciente.getHipertension().equalsIgnoreCase("Si") ? 100 : 0;
        int valor_mareo = nuevoPaciente.getMareo().equalsIgnoreCase("Si") ? 100 : 0;
        int valor_presion = medirPresion(nuevoPaciente.getPresion());
        int valor_fiebre = medirFiebre(nuevoPaciente.getFiebre());
        int valor_edad = medirEdad(nuevoPaciente.getEdad());
        int puntaje = valor_mareo + valor_edad + valor_presion + valor_fiebre + valor_hipertension;

        Date date = new Date(new java.util.Date().getTime());
        Color color = colorRepository.getColorBetweenRange(puntaje);

        Paciente paciente = new Paciente();
        paciente.setName(nuevoPaciente.getNombre());
        paciente.setDni(nuevoPaciente.getDni());
        paciente.setColor(color);
        paciente.setScore(puntaje);
        paciente.setStatus("");
        paciente.setStatus(cantidadSiendoAtendidos(color) < color.getCant_max_patient() ? "Siendo atendido" : "En espera");
        paciente.setDate(date);
        Paciente p = pacienteRepository.saveAndFlush(paciente);
        return p;
    }

    //Determina la cantidad de pacientes con estado Siendo Atendidos
    private int cantidadSiendoAtendidos(Color color) {
        int cantidad = pacienteRepository.cantidadPacientesSiendoAtendidos(color.getId());
        return cantidad;
    }

    //Evalua por edad
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

    //Evalua por presion
    public int medirPresion(int presion) {
        if (presion < 13) {
            return 30;
        } else if (13 <= presion && presion < 14) {
            return 0;
        } else {
            return 100;
        }
    }

    //Evalua por fiebre
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

    //Obtiene un paciente por id
    public Paciente obtenerPorId(long id) {
        return pacienteRepository.findById(id);
    }

    //Actualiza el estado a Atendido
    public Paciente actualizarEstadoAtendido(Paciente paciente) {
        paciente.setStatus("Atendido");
        Paciente p = pacienteRepository.save(paciente);

        // se libera un lugar
        Paciente proximoPaciente = pacienteRepository.proximoPacienteSiendoAtendido(paciente.getColor().getId());
        if (proximoPaciente != null) {
            actualizarEstadoSiendoAtendido(proximoPaciente);
        }
        return p;
    }

    //Actualiza el estado a Siendo Atendido (cuando se libera un lugar)
    public void actualizarEstadoSiendoAtendido(Paciente paciente) {
        paciente.setStatus("Siendo atendido");
        Paciente p = pacienteRepository.save(paciente);
    }

    //Lista por filtros
    public FilasPacientesDTO listarPorFiltros(FiltroDTO filtro) {
        List<Color> lc = colorRepository.findAll();
        lc.forEach(color -> {
            List<Paciente> lista = pacienteRepository.listarPorFiltros(filtro.getNombre(), filtro.getDni(), filtro.getEstado(), color.getId());
            seleccionarFila(lista, color.getColor());
        });
        return filas;
    }
}

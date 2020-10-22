package com.pacientesCovid19.restful.dto;
import com.pacientesCovid19.restful.model.Paciente;
import java.util.List;
/**
 *
 * @author Marcio
 */
public class FilasPacientesDTO {
    
    private List<Paciente> filaVerde;
    private List<Paciente> filaAmarillo;
    private List<Paciente> filaNaranja;
    private List<Paciente> filaRojo;

    public List<Paciente> getFilaVerde() {
        return filaVerde;
    }

    public void setFilaVerde(List<Paciente> filaVerde) {
        this.filaVerde = filaVerde;
    }

    public List<Paciente> getFilaAmarillo() {
        return filaAmarillo;
    }

    public void setFilaAmarillo(List<Paciente> filaAmarillo) {
        this.filaAmarillo = filaAmarillo;
    }

    public List<Paciente> getFilaNaranja() {
        return filaNaranja;
    }

    public void setFilaNaranja(List<Paciente> filaNaranja) {
        this.filaNaranja = filaNaranja;
    }

    public List<Paciente> getFilaRojo() {
        return filaRojo;
    }

    public void setFilaRojo(List<Paciente> filaRojo) {
        this.filaRojo = filaRojo;
    }
    
    
}

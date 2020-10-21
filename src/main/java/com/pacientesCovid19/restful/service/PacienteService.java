package com.pacientesCovid19.restful.service;

import com.pacientesCovid19.restful.model.Paciente;
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
    IPacienteImpl elementoDAO;
    
    @Transactional(readOnly = true) // operation read only
    public List<Paciente> listar() {
        return elementoDAO.findAll();
    }

    public Paciente agregar(Paciente paciente) {
        Date date = new Date(new java.util.Date().getTime());
        paciente.setDate(date);
        Paciente p = elementoDAO.saveAndFlush(paciente);
        return p;
    }

    public Paciente actualizar(Paciente paciente) {
        Paciente p = elementoDAO.save(paciente);
        return p;
    }
    
}

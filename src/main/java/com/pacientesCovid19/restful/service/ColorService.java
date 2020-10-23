package com.pacientesCovid19.restful.service;

import com.pacientesCovid19.restful.model.Color;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pacientesCovid19.restful.repository.IColorImpl;

/**
 *
 * @author Marcio
 */
@Service
public class ColorService {
    
    @Autowired
    IColorImpl elementoDAO;
    
    @Transactional(readOnly = true) // operation read only
    public List<Color> listar() {
        return elementoDAO.findAll();
    }

    public Color agregar(Color color) {
        Color elemento = elementoDAO.saveAndFlush(color);
        return elemento;
    }

    public Color actualizar(Color color) {
        Color elemento = elementoDAO.save(color);
        return elemento;
    }
}

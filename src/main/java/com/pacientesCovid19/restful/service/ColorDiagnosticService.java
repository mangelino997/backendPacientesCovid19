package com.pacientesCovid19.restful.service;

import com.pacientesCovid19.restful.model.ColorDiagnostic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pacientesCovid19.restful.repository.IColorDiagnosticImpl;

/**
 *
 * @author Marcio
 */
@Service
public class ColorDiagnosticService {
    
    @Autowired
    IColorDiagnosticImpl elementoDAO;
    
    @Transactional(readOnly = true) // operation read only
    public List<ColorDiagnostic> listar() {
        return elementoDAO.findAll();
    }

    public ColorDiagnostic agregar(ColorDiagnostic colorDiagnostic) {
        ColorDiagnostic c = elementoDAO.saveAndFlush(colorDiagnostic);
        return c;
    }

    public ColorDiagnostic actualizar(ColorDiagnostic colorDiagnostic) {
        ColorDiagnostic c = elementoDAO.save(colorDiagnostic);
        return c;
    }
}

package com.pacientesCovid19.restful.repository;

import com.pacientesCovid19.restful.model.ColorDiagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcio
 */
@Repository
public interface IColorDiagnosticImpl extends JpaRepository<ColorDiagnostic, Long>{
    
}

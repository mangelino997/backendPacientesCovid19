package com.pacientesCovid19.restful.repository;

import com.pacientesCovid19.restful.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Marcio
 */
@Repository
public interface IPacienteImpl extends JpaRepository<Paciente, Long>{
    
}

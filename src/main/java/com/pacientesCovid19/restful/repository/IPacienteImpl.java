package com.pacientesCovid19.restful.repository;

import com.pacientesCovid19.restful.model.Color;
import com.pacientesCovid19.restful.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Marcio
 */
@Repository
public interface IPacienteImpl extends JpaRepository<Paciente, Long>{
    
    @Query(value = "SELECT count(*) FROM Pacientes where color_id = :idColor AND status= 'siendo atendido'", nativeQuery = true)
    public int cantidadPacientesSiendoAtendidos(@Param("idColor") Long idColor);
}

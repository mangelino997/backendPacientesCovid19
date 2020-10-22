package com.pacientesCovid19.restful.repository;

import com.pacientesCovid19.restful.model.Color;
import com.pacientesCovid19.restful.model.Paciente;
import java.util.List;
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
    
    //Obtiene la cantidad de pacientes con estado "Siendo atendido" con determinado color
    @Query(value = "SELECT count(*) FROM Pacientes where color_id = :idColor AND status= 'Siendo atendido'", nativeQuery = true)
    public int cantidadPacientesSiendoAtendidos(@Param("idColor") Long idColor);
    
    //Obtiene el proximo paciente En espera que pasara a ser atendido
    @Query(value = "SELECT * FROM Pacientes where color_id = :idColor and status = 'En espera' limit 1;", nativeQuery = true)
    public Paciente proximoPacienteSiendoAtendido(@Param("idColor") Long idColor);
    
    //Obtiene una lista  por color y ordenada por fecha 
    public List<Paciente> findByColorIdOrderByDateAsc(long idColor);
    
    //Obtiene un paciente por id
    public Paciente findById(long id);
}

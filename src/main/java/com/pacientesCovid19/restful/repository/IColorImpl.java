package com.pacientesCovid19.restful.repository;

import com.pacientesCovid19.restful.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcio
 */
@Repository
public interface IColorImpl extends JpaRepository<Color, Long> {

    /* el intervalo es ( ] ya que no puede ser 0, pero si 500 (maximo) */
    @Query(value = "SELECT * FROM Colores WHERE :puntaje > range_min_value AND :puntaje <= range_max_value", nativeQuery = true)
    public Color getColorBetweenRange(@Param("puntaje") int puntaje);

}

package com.pacientesCovid19.restful.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author Marcio
 */
@Entity
@Table(name = "Colores")
public class Color {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    
    @Column(length = 20, nullable = false, unique = true)
    private String color;
    
    @Column(nullable = false)
    private Integer range_min_value;
    
    @Column(nullable = false)
    private Integer range_max_value;
    
    @Column(nullable = false)
    private Integer cant_max_patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getRange_min_value() {
        return range_min_value;
    }

    public void setRange_min_value(Integer range_min_value) {
        this.range_min_value = range_min_value;
    }

    public Integer getRange_max_value() {
        return range_max_value;
    }

    public void setRange_max_value(Integer range_max_value) {
        this.range_max_value = range_max_value;
    }

    public Integer getCant_max_patient() {
        return cant_max_patient;
    }

    public void setCant_max_patient(Integer cant_max_patient) {
        this.cant_max_patient = cant_max_patient;
    }
    
    
}

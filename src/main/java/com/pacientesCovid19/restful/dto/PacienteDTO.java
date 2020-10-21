package com.pacientesCovid19.restful.dto;
/**
 *
 * @author Marcio
 */
public class PacienteDTO {
    
    private String nombre;
    private int dni;
    private String mareo;
    private String hipertension;
    private int edad;
    private double fiebre;
    private int presion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMareo() {
        return mareo;
    }

    public void setMareo(String mareo) {
        this.mareo = mareo;
    }

    public String getHipertension() {
        return hipertension;
    }

    public void setHipertension(String hipertension) {
        this.hipertension = hipertension;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getFiebre() {
        return fiebre;
    }

    public void setFiebre(double fiebre) {
        this.fiebre = fiebre;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital;

/**
 *
 * @author Nayeli
 */
public class Medico { 
 
    private int numeroColegiatura;
    private String nombre;
    private String especialidad;

    public Medico(int numeroColegiatura, String nombre, String especialidad) {
        this.numeroColegiatura = numeroColegiatura;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    //Constructoroo
    
    public int getNumeroColegiatura() {
        return numeroColegiatura;
    }

    public void setNumeroColegiatura(int numeroColegiatura) {
        this.numeroColegiatura = numeroColegiatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    } 
}


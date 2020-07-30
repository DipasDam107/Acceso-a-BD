/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam107ABDe2;

import dam107ABDe1.*;
import java.time.LocalDate;

public class Empleado {
    private int id;
    private String nombre;
    private float salario;
    private LocalDate fechaNac;
    private String categoria;

    public Empleado(int id, String nombre, float salario, LocalDate fechaNac, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.fechaNac = fechaNac;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSalario() {
        return salario;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public String getCategoria() {
        return categoria;
    }
    

   
    
    
}

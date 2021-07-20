/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

/**
 *
 * @author David Castillo
 */
public class Prop_vehiculo {
     private String placa_vehiculo;
    private String nombre;
    private String telefono;
    private int id_propietario;
    private String direccion_domicilio;
    private String modelo;
    private String marca;

    public String getTelefono() {
        return telefono;
    }

    public Prop_vehiculo(int id_propietario,String placa_vehiculo, String nombre, String telefono , String direccion_domicilio,String marca, String modelo) {
               
        this.id_propietario = id_propietario;
        this.placa_vehiculo = placa_vehiculo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion_domicilio = direccion_domicilio;
        this.modelo = modelo;
        this.marca =  marca;
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

     
            
    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion_domicilio() {
        return direccion_domicilio;
    }

    public void setDireccion_domicilio(String direccion_domicilio) {
        this.direccion_domicilio = direccion_domicilio;
    }
    
}

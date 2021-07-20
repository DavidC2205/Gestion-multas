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
public class vehiculo  {
   private String marca;
    private String modelo;
    private String placa_vehiculo;
    private String nombre_propietario;
    private String direccion_propietario;
    private int id_propietario;
    private String telefono;
    private String email_prop;

    
    public vehiculo(int id_propietario,String nombre_propietario,String telefono,String direccion_propietario,String marca,String modelo,String placa_vehiculo,String email_prop ) {
        this.id_propietario= id_propietario;
        this.nombre_propietario = nombre_propietario;
        this.telefono=telefono;
        this.direccion_propietario = direccion_propietario;
        this.marca = marca;
        this.modelo=modelo;
        this.placa_vehiculo = placa_vehiculo;
        this.email_prop=email_prop;               
    }

    
     public vehiculo (){}
 
    public String getTelefono() {
        return telefono;
    }

    public String getEmail_prop() {
        return email_prop;
    }

    public void setEmail_prop(String email_prop) {
        this.email_prop = email_prop;
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

    
 
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getNombre_propietario() {
        return nombre_propietario;
    }

    public void setNombre_propietario(String nombre_propietario) {
        this.nombre_propietario = nombre_propietario;
    }

    public String getDireccion_propietario() {
        return direccion_propietario;
    }

    public void setDireccion_propietario(String direccion_propietario) {
        this.direccion_propietario = direccion_propietario;
    }
    
    
    
    
}

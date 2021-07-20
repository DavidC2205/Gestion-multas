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
public class Multa {
    
     
    private int id_expediente;
    private int id_infractor;
    private String nombre_infractor;
     private String placa_vehiculo;
    
     private String telefono;
   
    private String direccion;
    private String estado;
    private String email;
   
   
    private double costo;
    private String alcance;

    public Multa(int id_expediente, int id_infractor, String nombre_infractor, String placa_vehiculo, String telefono, String direccion, String email,String estado,String alcance,double costo) {
        this.id_expediente = id_expediente;
        this.id_infractor = id_infractor;
        this.nombre_infractor = nombre_infractor;
        this.placa_vehiculo = placa_vehiculo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.estado = estado;
        this.alcance = alcance;
        this.costo = costo;
        
    }

    public Multa() {
         }

    
    
    public int getId_expediente() {
        return id_expediente;
    }

    public void setId_expediente(int id_expediente) {
        this.id_expediente = id_expediente;
    }

    public int getId_infractor() {
        return id_infractor;
    }

    public void setId_infractor(int id_infractor) {
        this.id_infractor = id_infractor;
    }

    public String getNombre_infractor() {
        return nombre_infractor;
    }

    public void setNombre_infractor(String nombre_infractor) {
        this.nombre_infractor = nombre_infractor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String Estado) {
        this.estado = Estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }
     
    
    
    
}
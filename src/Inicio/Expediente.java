/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.util.Date;

/**
 *
 * @author David Castillo
 */
public class Expediente {
    
    private int id_expediente;
    private int id_infractor;
    private String nombre_infractor;
    private String telefono;
    private String direccion;
    private String Estado;
    private String email;
    private String placa_vehiculo;
    private Date fecha_limite;
    private String Alcance;

    
    
    public Expediente(int id_expediente, int id_infractor, String nombre_infractor, String placa_vehiculo, String direccion, String email,String telefono,String Estado,String Alcance,Date fecha_limite){
      this.id_expediente = id_expediente;
        this.id_infractor = id_infractor;
        this.nombre_infractor = nombre_infractor;
        this.placa_vehiculo = placa_vehiculo;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.Estado = Estado;
        this.Alcance = Alcance;
        this.fecha_limite = fecha_limite;
       
    
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
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
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

    public Date getFecha_limte() {
        return fecha_limite;
    }

    public void setFecha_limte(Date fecha_limte) {
        this.fecha_limite = fecha_limte;
    }

    public String getAlcance() {
        return Alcance;
    }

    public void setAlcance(String Alcance) {
        this.Alcance = Alcance;
    }
   
    
    
    
    
    
    
    
    
    
    
}

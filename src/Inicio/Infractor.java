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
public class Infractor {
 private   String nombre;
 private   String direccion_residencia;
 private   String telefono;
 private   String email;
 private   String vehiculo_propiedad;
 private   String placa_vehiculo;
 private int ide_infractor;

   // public Infractor(int leerIde_Infractor, String leerNombre, String leerPlaca_vehiculo, String leerDireccion, String leerEmail, String leerTelefono) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    public Infractor(int ide_infractor,String nombre,String placa_vehiculo , String direccion_residencia, String email,String telefono ) {
       
        this.ide_infractor= ide_infractor;
        this.nombre = nombre;
        this.placa_vehiculo = placa_vehiculo;
        this.direccion_residencia = direccion_residencia;
        this.email = email;
        this.telefono = telefono;
       
        
        
        
    }

    // }
   public Infractor() {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

    public int getIde_infractor() {
        return ide_infractor;
    }

    public void setIde_infractor(int ide_infractor) {
        this.ide_infractor = ide_infractor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion_residencia() {
        return direccion_residencia;
    }

    public void setDireccion_residencia(String direccion_residencia) {
        this.direccion_residencia = direccion_residencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehiculo_propiedad() {
        return vehiculo_propiedad;
    }

    public void setVehiculo_propiedad(String vehiculo_propiedad) {
        this.vehiculo_propiedad = vehiculo_propiedad;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }
    
    
}

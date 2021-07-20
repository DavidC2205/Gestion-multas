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
public class Denuncias {
    
    private int id_denuncia;
    private int id_infractor;
    private String nombre_infractor;
    private String telefono;
    private String direccion;
    private String Estado;
    private String email;
    private String modelo;
    private String marca;
    private String placa_vehiculo;
    private String Descripcion;

    public Denuncias(int id_denuncia,int id_infractor,String nombre_infractor, String placa_vehiculo, String direccion, String email, String telefono,String Estado) {
      // 
        this.id_denuncia=id_denuncia;  
        this.id_infractor = id_infractor;
        this.nombre_infractor = nombre_infractor;
        this.placa_vehiculo = placa_vehiculo;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.Estado=Estado;       

    }

    
    
    public Denuncias(){}
    
    
    public int getId_denuncia() {
        return id_denuncia;
    }

    public int getId_infractor() {
        return id_infractor;
    }

    public void setId_infractor(int id_infractor) {
        this.id_infractor = id_infractor;
    }

    public void setId_denuncia(int id_denuncia) {
        this.id_denuncia = id_denuncia;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}

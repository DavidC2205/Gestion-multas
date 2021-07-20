/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Funcionario {
    
    private int codigo;
    private String nombre;
    private String cargo;
    private Object descripcion;
    private String usuario;
    private String id;
    private String pass;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Funcionario(){}
    
    public Funcionario(int codigo, String nombre, String cargo, Object descripcion){
        this.codigo = codigo;
        this.nombre = nombre;
        this.cargo = cargo;
        this.descripcion = descripcion;
    }
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
   // public double getPrecio() {
     //   return precio;
    //}

    /**
     * @param precio the precio to set
     */
    //public void setPrecio(double precio) {
      //  this.precio = precio;
    //}

    /**
     * @return the descripcion
     */
    public Object getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }
    
}

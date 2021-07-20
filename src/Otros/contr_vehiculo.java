/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import Inicio.vehiculo;
import java.util.ArrayList;

/**
 *
 * @author David Castillo
 */
public class contr_vehiculo {
    private ArrayList<Object> a = new ArrayList<Object>();
    
    public contr_vehiculo(){}
    
    public contr_vehiculo(ArrayList<Object> a){
               this.a = a;
    }
    public void agregarRegistro(vehiculo v){
        this.a.add(v);
    }
    public void modificarRegistro(int c, vehiculo v){
        this.a.set(c, v);
    }
    
    public void eliminarRegistro(int c){
        this.a.remove(c);
    }
    
    public vehiculo obtenerRegistro(int c){
        return (vehiculo)a.get(c);
    }
    
    
    public int cantidadRegistro(){
        return this.a.size();
    }
    
    public int buscaCodigo(int id_propietario){
        for(int c = 0; c < cantidadRegistro(); c++){
            if(id_propietario == obtenerRegistro(c).getId_propietario())return c;
        }
        return -1;
    }
    
    }
    
    


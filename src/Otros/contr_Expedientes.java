/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;
import Inicio.Expediente;
import java.util.ArrayList;

/**
 *
 * @author David Castillo
 */
public class contr_Expedientes {
    
    
    private ArrayList<Object> a = new ArrayList<Object>();
    
    public contr_Expedientes(){}
    
    public contr_Expedientes(ArrayList<Object> a){
               this.a = a;
    }
    public void agregarRegistro(Expediente i){
        this.a.add(i);
    }
    public void modificarRegistro(int c, Expediente i){
        this.a.set(c, i);
    }
    
    public void eliminarRegistro(int c){
        this.a.remove(c);
    }
    
    public Expediente obtenerRegistro(int c){
        return (Expediente)a.get(c);
    }
    
    
    public int cantidadRegistro(){
        return this.a.size();
    }
    
    public int buscaCodigo(int id_infractor){
        for(int c = 0; c < cantidadRegistro(); c++){
            if(id_infractor == obtenerRegistro(c).getId_expediente())return c;
        }
        return -1;
    }
    
    }

    
    


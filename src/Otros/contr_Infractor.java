/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import Inicio.Infractor;
import java.util.ArrayList;

/**
 *
 * @author David Castillo
 */
public class contr_Infractor {
    private ArrayList<Object> a = new ArrayList<Object>();
    
    public contr_Infractor(){}
    
    public contr_Infractor(ArrayList<Object> a){
               this.a = a;
    }
    public void agregarRegistro(Infractor i){
        this.a.add(i);
    }
    public void modificarRegistro(int c, Infractor i){
        this.a.set(c, i);
    }
    
    public void eliminarRegistro(int c){
        this.a.remove(c);
    }
    
    public Infractor obtenerRegistro(int c){
        return (Infractor)a.get(c);
    }
    
    
    public int cantidadRegistro(){
        return this.a.size();
    }
    
    public int buscaCodigo(int id_infractor){
        for(int c = 0; c < cantidadRegistro(); c++){
            if(id_infractor == obtenerRegistro(c).getIde_infractor())return c;
        }
        return -1;
    }
    
    }
    
    


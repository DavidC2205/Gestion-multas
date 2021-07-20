/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import Inicio.Denuncias;
import java.util.ArrayList;

/**
 *
 * @author David Castillo
 */
public class contr_denuncias {
    
    private ArrayList<Object> a = new ArrayList<Object>();
    
    public contr_denuncias(){}
    
    public contr_denuncias(ArrayList<Object> a){
               this.a = a;
    }
    public void agregarRegistro(Denuncias i){
        this.a.add(i);
    }
    public void modificarRegistro(int c, Denuncias i){
        this.a.set(c, i);
    }
    
    public void eliminarRegistro(int c){
        this.a.remove(c);
    }
    
    public Denuncias obtenerRegistro(int c){
        return (Denuncias)a.get(c);
    }
    
    
    public int cantidadRegistro(){
        return this.a.size();
    }
    
    public int buscaCodigo(int id_denuncia){
        for(int c = 0; c < cantidadRegistro(); c++){
            if(id_denuncia == obtenerRegistro(c).getId_denuncia())return c;
        }
        return -1;
    }
    
    }


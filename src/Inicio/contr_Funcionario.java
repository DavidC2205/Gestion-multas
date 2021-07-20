/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class contr_Funcionario {
    
    private ArrayList<Object> a = new ArrayList<Object>();
    
    public contr_Funcionario(){}
    
    public contr_Funcionario(ArrayList<Object> a){
        this.a = a;
    }
    
    public void agregarRegistro(Funcionario p){
        this.a.add(p);
    }

    public void modificarRegistro(int i, Funcionario p){
        this.a.set(i, p);
    }
    
    public void eliminarRegistro(int i){
        this.a.remove(i);
    }
    
    public Funcionario obtenerRegistro(int i){
        return (Funcionario)a.get(i);
    }
    
    public int cantidadRegistro(){
        return this.a.size();
    }
    
    public int buscaCodigo(int codigo){
        for(int i = 0; i < cantidadRegistro(); i++){
            if(codigo == obtenerRegistro(i).getCodigo())return i;
        }
        return -1;
    }
    
}

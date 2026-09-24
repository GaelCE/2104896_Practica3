package com.example._104896_practica3.Modelo;

import java.util.Random;

public class Dado {
    int valor;
    int caras;
    boolean seleccionado;

    Dado(){
        caras = 6;
        valor = 1;
        seleccionado=false;
    }

    public void lanzar(){
        Random rand = new Random();
        valor = rand.nextInt(1,caras+1);
    }

    public int getValor(){
        return valor;
    }

    public boolean getSeleccionado(){
        return seleccionado;
    }

    public void setSeleccionado(boolean nuevoSeleccionado){
        seleccionado=nuevoSeleccionado;
    }

    public String toString(){
        return "" + valor;
    }
}
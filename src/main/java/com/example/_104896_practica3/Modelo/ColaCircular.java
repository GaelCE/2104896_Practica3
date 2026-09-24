package com.example._104896_practica3.Modelo;

import java.util.ArrayList;

public class ColaCircular <T>{
    private int inicio;
    private  int fin;
    private T[] cola;

    public ColaCircular(){
        inicio=-1;
        fin =-1;
        cola=(T[]) new Object[10];
    }

    public ColaCircular(int capacidad){
        inicio=-1;
        fin =-1;
        cola=(T[]) new Object[capacidad];
    }

    public void insertarDato(T o) {
        if (estaLlena()) {
            System.out.println("DESBORDAMIENTO");
            ampliarCola();
        }
        if (limite(fin)) {
            fin = 0;
        } else {
            fin += 1;
        }
        cola[fin] = o;
        if (inicio == -1) {
            inicio = 0;
        }
    }

    public T eliminarDato(){
        T dato;
        if (estaVacia()){
            System.out.println("SUBDESBORDAMIENTO");
            return null;
        }
        dato = cola[inicio];
        if (inicio== fin){
            inicio=-1;
            fin =-1;
        } else {
            if (limite(inicio)){
                inicio=0;
            } else {
                inicio+=1;
            }
        }
        return dato;
    }

    public ArrayList<T> obtenerCola(){
        ArrayList<T>auxiliar=new ArrayList<>();
        if(estaVacia()){
            System.out.println("Cola vacia");
            return auxiliar;
        } else {
            if (inicio== fin){
                System.out.println(cola[inicio]);
                auxiliar.add(cola[inicio]);
            } else {
                int indice=inicio;
                auxiliar.add(cola[inicio]);
                while (indice!= fin){
                    if (limite(indice)){
                        indice=0;
                    } else {
                        indice++;
                    }
                    auxiliar.add(cola[indice]);
                }
            }
            return auxiliar;
        }
    }

    public boolean estaVacia(){
        return inicio==-1;
    }

    public boolean estaLlena(){
        return (fin == cola.length-1 && inicio == 0) || (fin + 1 == inicio);
    }

    private boolean limite(int valor){
        return valor==cola.length-1;
    }

    public int getEspaciosDisponibles(){
        if (inicio==-1){
            return cola.length;
        } else {
            if (inicio<= fin){
                return cola.length-1- fin +inicio;
            } else {
                return inicio- fin -1;
            }
        }
    }

    public int getEspaciosOcupados(){
        return cola.length-getEspaciosDisponibles();
    }

    public T verInicio(){
        if (!estaVacia()){
            return cola[inicio];
        } else {
            return null;
        }
    }

    private void ampliarCola() {
        int tamanoAnterior = cola.length;
        int capacidad = (int) (tamanoAnterior * 1.5);
        if (capacidad == tamanoAnterior) {
            capacidad = tamanoAnterior + 1;
        }
        T[] auxiliar = (T[]) new Object[capacidad];
        int indice = inicio;
        for (int i = 0; i < tamanoAnterior; i++) {
            auxiliar[i] = cola[indice];
            if (indice == tamanoAnterior - 1) {
                indice = 0;
            } else {
                indice++;
            }
        }
        cola = auxiliar;
        inicio = 0;
        fin = tamanoAnterior - 1;
    }
}

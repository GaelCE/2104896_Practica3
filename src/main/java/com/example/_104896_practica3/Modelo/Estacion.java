package com.example._104896_practica3.Modelo;

import java.util.ArrayList;

public class Estacion {
    private String nombre;
    private ColaCircular<Integer> colaCircular;
    private ArrayList<Dado>dados;

    public Estacion(String nombre,boolean tieneCola){
        this.nombre=nombre;
        dados=new ArrayList<>();
        dados.add(new Dado());
        if (tieneCola){
            this.colaCircular=new ColaCircular<>(50);
        }
    }

    public void agregarDado(){
        dados.add(new Dado());
    }

    public void eliminarDado() {
        if (!dados.isEmpty()) {
            dados.removeFirst();
        }
    }

    public void tirarDados(){
        for(int i=0;i<dados.size();i++){
            dados.get(i).lanzar();
        }
    }

    public int getAProcesar(){
        int suma=0;
        for (int i=0;i<dados.size();i++){
            suma+=dados.get(i).getValor();
        }
        return suma;
    }

    public int getTamanoCola(){
        return colaCircular.getEspaciosOcupados();
    }

    public int getTamanoFila(){
        if (colaCircular ==null){
            return 0;
        } else {
            return colaCircular.getEspaciosOcupados();
        }
    }

    public void insertarCliente(int turnoEntrada){
        if (colaCircular!=null) {
            colaCircular.insertarDato(turnoEntrada);
        }
    }

    public Integer sacarCliente(){
        if (colaCircular==null){
            return null;
        } else {
            return colaCircular.eliminarDato();
        }
    }

    public ArrayList<Dado>getDados(){
        return dados;
    }

    public String getNombre(){
        return nombre;
    }
}

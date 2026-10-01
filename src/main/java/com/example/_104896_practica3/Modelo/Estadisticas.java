package com.example._104896_practica3.Modelo;

import java.util.ArrayList;

public class Estadisticas{
    private ArrayList<Integer> tiemposEnSistema;
    private ArrayList<Integer> salidosPorTurno;
    private ArrayList<Integer> enSistemaPorTurno;
    private ArrayList<ArrayList<Integer>> rolledPorTurno;
    private ArrayList<ArrayList<Integer>> movedPorTurno;

    public Estadisticas(){
        tiemposEnSistema=new ArrayList<>();
        salidosPorTurno=new ArrayList<>();
        enSistemaPorTurno=new ArrayList<>();
        rolledPorTurno=new ArrayList<>();
        movedPorTurno=new ArrayList<>();
    }

    public void registrarInicial(int enSistema){
        enSistemaPorTurno.add(enSistema);
    }

    public void registrarSalida(int tiempo){
        tiemposEnSistema.add(tiempo);
    }

    public void registrarTurno(int salidos,int enSistema,ArrayList<Integer> rolled,ArrayList<Integer> moved){
        salidosPorTurno.add(salidos);
        enSistemaPorTurno.add(enSistema);
        rolledPorTurno.add(rolled);
        movedPorTurno.add(moved);
    }

    public ArrayList<Integer> getTiemposEnSistema(){
        return tiemposEnSistema;
    }

    public ArrayList<Integer> getSalidosPorTurno(){
        return salidosPorTurno;
    }

    public ArrayList<Integer> getEnSistemaPorTurno(){
        return enSistemaPorTurno;
    }

    public ArrayList<ArrayList<Integer>> getRolledPorTurno(){
        return rolledPorTurno;
    }

    public ArrayList<ArrayList<Integer>> getMovedPorTurno(){
        return movedPorTurno;
    }
}
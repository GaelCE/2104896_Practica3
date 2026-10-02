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

    public ArrayList<Integer> getEnSistemaPorTurno(){
        return enSistemaPorTurno;
    }

    public ArrayList<Integer> getSalidosAcumulados(){
        ArrayList<Integer> acumulados=new ArrayList<>();
        int acumulado=0;
        for (int i=0;i<salidosPorTurno.size();i++){
            acumulado+=salidosPorTurno.get(i);
            acumulados.add(acumulado);
        }
        return acumulados;
    }

    public ArrayList<Integer> getRolledDeEstacion(int indice){
        ArrayList<Integer> resultado=new ArrayList<>();
        for (ArrayList<Integer> turno : rolledPorTurno){
            resultado.add(turno.get(indice));
        }
        return resultado;
    }

    public ArrayList<Integer> getMovedDeEstacion(int indice){
        ArrayList<Integer> resultado=new ArrayList<>();
        for (ArrayList<Integer> turno : movedPorTurno){
            resultado.add(turno.get(indice));
        }
        return resultado;
    }

    public ArrayList<Double> getRolledPromedio(){
        ArrayList<Double> resultado=new ArrayList<>();
        for (ArrayList<Integer> turno : rolledPorTurno){
            int suma=0;
            for (int valor : turno){
                suma+=valor;
            }
            resultado.add(suma/10.0);
        }
        return resultado;
    }

    public ArrayList<Double> getMovedPromedio(){
        ArrayList<Double> resultado=new ArrayList<>();
        for (ArrayList<Integer> turno : movedPorTurno){
            int suma=0;
            for (int valor : turno){
                suma+=valor;
            }
            resultado.add(suma/10.0);
        }
        return resultado;
    }
}
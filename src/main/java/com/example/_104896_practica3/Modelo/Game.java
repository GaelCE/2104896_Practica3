package com.example._104896_practica3.Modelo;

import java.util.ArrayList;

public class Game {
    private ArrayList<Estacion> estaciones;
    private int turno;

    public Game() {
        estaciones = new ArrayList<>();
        estaciones.add(new Estacion("1", false));
        for (int i = 2; i < 11; i++) {
            Estacion nueva = new Estacion(""+i,true);
            estaciones.add(nueva);
            for (int j=0;j<4;j++) {
                nueva.insertarCliente(0);
            }
        }
        turno=0;
    }

    public void tirarDados(){
        for (Estacion estacion:estaciones){
            estacion.tirarDados();
        }
    }

    public void mover() {
        turno++;
        ArrayList<Integer> rolledDelTurno=new ArrayList<>();
        ArrayList<Integer> movedDelTurno=new ArrayList<>();

        Estacion ultima=estaciones.get(9);
        moverHaciaSalida(ultima, rolledDelTurno, movedDelTurno);

        for (int i=8;i>=1;i--) {
            Estacion origen=estaciones.get(i);
            Estacion destino=estaciones.get(i + 1);
            moverEntreEstaciones(origen, destino, rolledDelTurno, movedDelTurno);
        }

        Estacion primera=estaciones.get(0);
        Estacion segunda=estaciones.get(1);
        int rolledPrimera=primera.getAProcesar();
        rolledDelTurno.add(0,rolledPrimera);
        int movidosPrimera=0;
        for (int i=0;i<rolledPrimera;i++) {
            segunda.insertarCliente(turno);
            movidosPrimera++;
        }
        movedDelTurno.add(0,movidosPrimera);
    }

    public void moverDados(Estacion origen, Estacion destino){
        origen.eliminarDado();
        destino.agregarDado();
    }

    private void moverEntreEstaciones(Estacion origen, Estacion destino, ArrayList<Integer> rolled, ArrayList<Integer> moved) {
        int aProcesar=origen.getAProcesar();
        rolled.add(0,aProcesar);
        int movidos=0;
        if (aProcesar>origen.getTamanoFila()) {
            for(int i=0;i<origen.getTamanoFila();i++){
                Integer turnoEntrada=origen.sacarCliente();
                destino.insertarCliente(turnoEntrada);
                movidos++;
            }
        }else{
            for(int i=0;i<aProcesar;i++) {
                Integer turnoEntrada=origen.sacarCliente();
                destino.insertarCliente(turnoEntrada);
                movidos++;
            }
        }
        moved.add(0,movidos);
    }

    private void moverHaciaSalida(Estacion ultima, ArrayList<Integer> rolled, ArrayList<Integer> moved) {
        int aProcesar=ultima.getAProcesar();
        rolled.add(0,aProcesar);
        int salida=0;
        if(aProcesar>ultima.getTamanoFila()) {
            for(int i=0;i<ultima.getTamanoFila();i++){
                ultima.sacarCliente();
                salida++;
            }
        }else{
            for(int i=0;i<aProcesar;i++) {
                ultima.sacarCliente();
                salida++;
            }
        }
        moved.add(0,salida);
    }

    public ArrayList<Estacion> getEstaciones() {
        return new ArrayList<>(estaciones);
    }

    public int getTurno(){
        return turno;
    }
}

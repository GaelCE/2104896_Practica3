package com.example._104896_practica3.Controlador;

import com.example._104896_practica3.Modelo.Estacion;
import com.example._104896_practica3.Modelo.Game;

import java.util.ArrayList;

public class Controlador {
    private Game game;
    private boolean mover;

    public Controlador(){
        game=new Game();
        mover=false;
    }

    public void tirarDados(){
        game.tirarDados();
        mover=true;
    }

    public void moverClientes(){
        game.mover();
        mover=false;
    }

    public void moverDados(int origen, int destino) {
        Estacion estacionOrigen=game.getEstaciones().get(origen);
        Estacion estacionDestino=game.getEstaciones().get(destino);
        game.moverDados(estacionOrigen, estacionDestino);
    }

    public boolean getMover(){
        return mover;
    }

    public ArrayList<Estacion> getEstaciones(){
        return game.getEstaciones();
    }
}

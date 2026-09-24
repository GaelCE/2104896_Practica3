package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Modelo.Dado;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DadoImage extends Label {

    private double width = 80;
    private double height = 80;
    private Dado dado;
    private ImageView view;

    public DadoImage(Dado dado) {
        this.dado = dado;
        crearImagenDado();
    }

    private void crearImagenDado() {
        Image imagen = new Image(getClass().getResourceAsStream(obtenerRuta()));
        view = new ImageView(imagen);
        view.setFitWidth(width);
        view.setFitHeight(height);
        setGraphic(view);
    }

    public void actualizar() {
        String ruta = "/dados/dado" + dado.getValor() + ".png";
        view.setImage(new Image(getClass().getResourceAsStream(ruta)));
    }

    public String toString(){
        return dado.toString();
    }

    public void setWidthAndHeight(double w, double h){
        width = w;
        height = h;
        view.setFitWidth(w);
        view.setFitHeight(h);
    }

    private String obtenerRuta(){
        return "/dados/dado"+dado.getValor()+".png";
    }
}

package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Modelo.Dado;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cliente extends Label {

    private double width = 15;
    private double height = 15;
    private ImageView view;

    public Cliente() {
        crearImagenCliente();
    }

    private void crearImagenCliente() {
        Image imagen = new Image(getClass().getResourceAsStream(obtenerRuta()));
        view=new ImageView(imagen);
        view.setFitWidth(width);
        view.setFitHeight(height);
        setGraphic(view);
    }


    private String obtenerRuta(){
        return "/recursos/Circulo_azul.png";
    }
}

package com.example._104896_practica3.Vista;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cliente extends Label {

    private static final Image IMAGEN=new Image(Cliente.class.getResourceAsStream("/recursos/Circulo_azul.png"));
    private double width = 15;
    private double height = 15;
    private ImageView view;

    public Cliente() {
        crearImagenCliente();
    }

    private void crearImagenCliente() {
        view=new ImageView(IMAGEN);
        view.setFitWidth(width);
        view.setFitHeight(height);
        setGraphic(view);
    }
}

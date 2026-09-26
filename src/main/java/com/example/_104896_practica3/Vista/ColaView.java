package com.example._104896_practica3.Vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColaView extends VBox{

    public ColaView(int clientes){
        dibujarCola(clientes);
    }

    public void dibujarCola(int clientes) {
        getChildren().clear();
        int filas=clientes/5;
        for (int i=0;i<filas;i++){
            HBox hBox=new HBox();
            for(int j=0;j<5;j++) {
                hBox.getChildren().add(new Cliente());
            }
            getChildren().add(hBox);
        }
        int residuo=clientes%5;
        if(residuo>0){
            HBox hBox=new HBox();
            for(int i=0;i<residuo;i++) {
                hBox.getChildren().add(new Cliente());
            }
            getChildren().add(hBox);
        }
    }
}

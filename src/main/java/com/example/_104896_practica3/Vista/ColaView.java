package com.example._104896_practica3.Vista;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColaView extends VBox{
    private Orientacion orientacion;

    public ColaView(int clientes, Orientacion orientacion) {
        this.orientacion = orientacion;
        dibujarCola(clientes);
    }

    public void dibujarCola(int clientes) {
        getChildren().clear();
        switch(orientacion){
            case HACIA_ABAJO:
                dibujarHaciaAbajo(clientes);
                break;
            case HACIA_ARRIBA:
                dibujarHaciaArriba(clientes);
                break;
            case HACIA_LA_DERECHA:
                dibujarHaciaLaDerecha(clientes);
                break;
        }
    }

    private void dibujarHaciaAbajo(int clientes) {
        int filas=clientes/5;
        for (int i=0;i<filas;i++) {
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

    private void dibujarHaciaArriba(int clientes) {
        int filas=clientes/5;
        for(int i=0;i<filas;i++){
            HBox hBox=new HBox();
            for (int j=0;j<5;j++) {
                hBox.getChildren().add(new Cliente());
            }
            getChildren().add(0, hBox);
        }
        int residuo=clientes%5;
        if(residuo>0){
            HBox hBox=new HBox();
            for(int i=0;i<residuo;i++) {
                hBox.getChildren().add(new Cliente());
            }
            getChildren().add(0,hBox);
        }
    }

    private void dibujarHaciaLaDerecha(int clientes) {
        HBox contenedor=new HBox();
        int columnas=clientes/5;
        for(int i=0;i<columnas;i++) {
            VBox vBox=new VBox();
            for(int j=0;j<5;j++) {
                vBox.getChildren().add(new Cliente());
            }
            contenedor.getChildren().add(vBox);
        }
        int residuo=clientes%5;
        if(residuo>0){
            VBox vBox=new VBox();
            for(int i=0;i<residuo;i++) {
                vBox.getChildren().add(new Cliente());
            }
            contenedor.getChildren().add(vBox);
        }
        getChildren().add(contenedor);
    }
}

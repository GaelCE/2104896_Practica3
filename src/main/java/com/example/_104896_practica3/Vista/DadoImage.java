package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Modelo.Dado;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DadoImage extends Label {

    private static final Image[] IMAGENES=new Image[6];
    static{
        for(int i=0;i<6;i++){
            IMAGENES[i]=new Image(DadoImage.class.getResourceAsStream("/dados/dado"+(i+1)+".png"));
        }
    }
    private double width = 50;
    private double height = 50;
    private Dado dado;
    private ImageView view;

    public DadoImage(Dado dado) {
        this.dado = dado;
        crearImagenDado();
    }

    private void crearImagenDado(){
        view=new ImageView(IMAGENES[dado.getValor()-1]);
        view.setFitWidth(width);
        view.setFitHeight(height);
        setGraphic(view);
    }

    public void actualizar(){
        view.setImage(IMAGENES[dado.getValor()-1]);
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

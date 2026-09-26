package com.example._104896_practica3.Vista;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class EstacionView extends VBox {
    private Cara cara;
    private ColaView colaView;
    private HBox HBdados;
    private Label emoji;

    public EstacionView(int seleccionCara, int clientes, ArrayList<DadoImage> dadoImage) {
        this.cara = new Cara(seleccionCara);
        colaView = new ColaView(clientes);
        HBdados = new HBox();
        emoji = new Label(cara.getEmoji());
        dibujarEstacionView(clientes, dadoImage);
    }

    private void actualizarCola(int cantidad){
        colaView.dibujarCola(cantidad);
    }

    private void actualizarDados(ArrayList<DadoImage>dados){
        HBdados.getChildren().clear();
        for (DadoImage dadoImage : dados){
            HBdados.getChildren().add(dadoImage);
        }
    }

    public void dibujarEstacionView(int cantidad,ArrayList<DadoImage>dados){
        getChildren().clear();
        actualizarCola(cantidad);
        actualizarDados(dados);
        getChildren().add(emoji);
        getChildren().add(HBdados);
        getChildren().add(colaView);
    }
}

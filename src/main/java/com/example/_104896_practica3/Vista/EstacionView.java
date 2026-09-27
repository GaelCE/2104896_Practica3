package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Modelo.Dado;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class EstacionView extends VBox{
    private Cara cara;
    private ColaView colaView;
    private HBox HBdados;
    private Label emoji;
    private Orientacion orientacion;
    private ArrayList<DadoImage> dadosImage;

    public EstacionView(int seleccionCara,int clientes,ArrayList<Dado> dados,Orientacion orientacion){
        this.orientacion=orientacion;
        this.cara=new Cara(seleccionCara);
        colaView=new ColaView(clientes,orientacion);
        HBdados=new HBox();
        emoji=new Label(cara.getEmoji());
        dadosImage=crearDadosImage(dados);
        dibujarEstacionView(clientes);
        emoji.setStyle("-fx-font-size:100px;");
    }

    private ArrayList<DadoImage> crearDadosImage(ArrayList<Dado> dados){
        ArrayList<DadoImage> lista=new ArrayList<>();
        for (Dado dado : dados){
            lista.add(new DadoImage(dado));
        }
        return lista;
    }

    private void actualizarCola(int cantidad){
        colaView.dibujarCola(cantidad);
    }

    public void actualizarValoresDados(){
        for (DadoImage dadoImage : dadosImage){
            dadoImage.actualizar();
        }
    }

    public void actualizarCantidadDados(ArrayList<Dado> dados){
        dadosImage=crearDadosImage(dados);
        HBdados.getChildren().clear();
        for (DadoImage dadoImage : dadosImage){
            HBdados.getChildren().add(dadoImage);
        }
    }

    public void dibujarEstacionView(int cantidad){
        getChildren().clear();
        actualizarCola(cantidad);
        HBdados.getChildren().clear();
        for (DadoImage dadoImage : dadosImage){
            HBdados.getChildren().add(dadoImage);
        }
        switch (orientacion){
            case HACIA_ABAJO:
                getChildren().add(emoji);
                getChildren().add(HBdados);
                getChildren().add(colaView);
                break;
            case HACIA_ARRIBA:
                getChildren().add(colaView);
                getChildren().add(HBdados);
                getChildren().add(emoji);
                break;
            case HACIA_LA_DERECHA:
                HBox fila=new HBox();
                fila.getChildren().add(emoji);
                fila.getChildren().add(HBdados);
                fila.getChildren().add(colaView);
                getChildren().add(fila);
                break;
        }
    }
}

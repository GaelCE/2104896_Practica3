package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Modelo.Dado;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class EstacionView extends VBox{
    private Cara cara;
    private ColaView colaView;
    private VBox VBdados;
    private Label emoji;
    private Orientacion orientacion;
    private ArrayList<DadoImage> dadosImage;

    public EstacionView(int seleccionCara,int clientes,ArrayList<Dado> dados,Orientacion orientacion){
        this.orientacion=orientacion;
        this.cara=new Cara(seleccionCara);
        colaView=new ColaView(clientes,orientacion);
        VBdados=new VBox();
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
        dibujarDados();
    }

    private void dibujarDados(){
        VBdados.getChildren().clear();
        int filas=dadosImage.size()/2;
        for (int i=0;i<filas;i++){
            HBox hBox=new HBox();
            hBox.getChildren().add(dadosImage.get(i*2));
            hBox.getChildren().add(dadosImage.get(i*2+1));
            VBdados.getChildren().add(hBox);
        }
        int residuo=dadosImage.size()%2;
        if (residuo>0){
            HBox hBox=new HBox();
            hBox.getChildren().add(dadosImage.get(dadosImage.size()-1));
            VBdados.getChildren().add(hBox);
        }
    }

    public void dibujarEstacionView(int cantidad){
        getChildren().clear();
        actualizarCola(cantidad);
        dibujarDados();
        switch (orientacion){
            case HACIA_ABAJO:
                getChildren().add(emoji);
                getChildren().add(VBdados);
                break;
            case HACIA_ARRIBA:
                getChildren().add(VBdados);
                getChildren().add(emoji);
                break;
            case HACIA_LA_DERECHA:
                HBox fila=new HBox();
                fila.getChildren().add(emoji);
                fila.getChildren().add(VBdados);
                getChildren().add(fila);
                break;
        }
    }

    public ColaView getColaView(){
        return colaView;
    }
}

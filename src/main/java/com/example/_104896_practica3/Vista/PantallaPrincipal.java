package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Controlador.Controlador;
import com.example._104896_practica3.Modelo.Estacion;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class PantallaPrincipal {
    private Controlador controlador;
    private ScrollPane scrollPane;
    private AnchorPane anchorPane;
    private ArrayList<EstacionView> estaciones;
    private ImageButton boton;
    private Label lbTurno;
    private Label lbSalidos;
    private Label lbEnSistema;
    private int origenSeleccionado=-1;
    private static final double RESX=1920.0;
    private static final double RESGAEL=1080.0;

    public PantallaPrincipal(Controlador controlador){
        this.controlador=controlador;
        scrollPane=new ScrollPane();
        anchorPane=new AnchorPane();
        estaciones=new ArrayList<>();
        construirPantallaJuego();
        scrollPane.setContent(anchorPane);
    }

    private void construirPantallaJuego(){
        anchorPane.getChildren().clear();

        ImageView background=new ImageView(new Image(getClass().getResourceAsStream("/recursos/BackgroundGame.png")));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(anchorPane.widthProperty());
        background.fitHeightProperty().bind(anchorPane.heightProperty());

        boton=new ImageButton("/recursos/btnRoll.png","/recursos/btnRoll.png",370,200);
        boton.setOnAction(e->{
            if (origenSeleccionado!=-1){
                estaciones.get(origenSeleccionado).setStyle("");
                origenSeleccionado=-1;
            }
            if (controlador.getMover()){
                controlador.moverClientes();
                boton.setImagen("/recursos/btnRoll.png");
            } else {
                controlador.tirarDados();
                boton.setImagen("/recursos/btnMove.png");
            }
            actualizarPantalla();
        });

        lbTurno=new Label("0");
        lbSalidos=new Label("0");
        lbEnSistema=new Label("0");

        anchorPane.getChildren().addAll(background,boton,lbTurno,lbSalidos,lbEnSistema);
        dibujarEstaciones();
        posicionarEnPane(boton,1000/RESX,800/RESGAEL);
        posicionarEnPane(lbTurno,850/RESX,50/RESGAEL);
        posicionarEnPane(lbSalidos,850/RESX,90/RESGAEL);
        posicionarEnPane(lbEnSistema,850/RESX,130/RESGAEL);
    }

    private void dibujarEstaciones(){
        estaciones=new ArrayList<>();
        ArrayList<Estacion> estacionesModelo=controlador.getEstaciones();
        for (int i=0;i<estacionesModelo.size();i++){
            Estacion estacion=estacionesModelo.get(i);
            Orientacion orientacion;
            if (i<4){
                orientacion=Orientacion.HACIA_ABAJO;
            } else if (i<6){
                orientacion=Orientacion.HACIA_LA_DERECHA;
            } else {
                orientacion=Orientacion.HACIA_ARRIBA;
            }
            EstacionView estacionView=new EstacionView(i+1,estacion.getTamanoFila(),estacion.getDados(),orientacion);
            int indiceFinal=i;
            estacionView.setOnMouseClicked(e->seleccionarEstacion(indiceFinal));
            estaciones.add(estacionView);
            anchorPane.getChildren().add(estacionView);
        }
        posicionarEnPane(estaciones.get(0),100/RESX,100/RESGAEL);
        posicionarEnPane(estaciones.get(1),300/RESX,100/RESGAEL);
        posicionarEnPane(estaciones.get(2),500/RESX,100/RESGAEL);
        posicionarEnPane(estaciones.get(3),700/RESX,100/RESGAEL);
        posicionarEnPane(estaciones.get(4),900/RESX,100/RESGAEL);
        posicionarEnPane(estaciones.get(5),90,60);
        posicionarEnPane(estaciones.get(6),70,90);
        posicionarEnPane(estaciones.get(7),50,90);
        posicionarEnPane(estaciones.get(8),30,90);
        posicionarEnPane(estaciones.get(9),10,90);
    }

    private void seleccionarEstacion(int indice){
        if (origenSeleccionado==-1){
            origenSeleccionado=indice;
            estaciones.get(indice).setStyle("-fx-border-color:red;-fx-border-width:3;");
        } else {
            controlador.moverDados(origenSeleccionado,indice);
            estaciones.get(origenSeleccionado).setStyle("");
            ArrayList<Estacion> estacionesModelo=controlador.getEstaciones();
            estaciones.get(origenSeleccionado).actualizarCantidadDados(estacionesModelo.get(origenSeleccionado).getDados());
            estaciones.get(indice).actualizarCantidadDados(estacionesModelo.get(indice).getDados());
            origenSeleccionado=-1;
        }
    }

    private void actualizarPantalla(){
        ArrayList<Estacion> estacionesModelo=controlador.getEstaciones();
        for (int i=0;i<estaciones.size();i++){
            Estacion estacion=estacionesModelo.get(i);
            estaciones.get(i).actualizarValoresDados();
            estaciones.get(i).dibujarEstacionView(estacion.getTamanoFila());
        }
        lbTurno.setText(""+controlador.getTurno());
    }

    private void posicionarEnPane(Node nodo,double porcentajeX,double porcentajeY){
        nodo.translateXProperty().unbind();
        nodo.translateYProperty().unbind();
        nodo.translateXProperty().bind(anchorPane.widthProperty().multiply(porcentajeX));
        nodo.translateYProperty().bind(anchorPane.heightProperty().multiply(porcentajeY));
    }

    public ScrollPane getScrollPane(){
        return scrollPane;
    }
}
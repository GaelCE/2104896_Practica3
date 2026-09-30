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
    private ColaView salidos;
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

        boton=new ImageButton("/recursos/btnRoll.png","/recursos/btnRoll.png",170,100);
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
        lbTurno.setStyle("-fx-font-size:100px;");
        lbSalidos=new Label("0");
        lbSalidos.setStyle("-fx-font-size:100px;");
        lbEnSistema=new Label("0");
        lbEnSistema.setStyle("-fx-font-size:100px;");

        anchorPane.getChildren().addAll(background,boton,lbTurno,lbSalidos,lbEnSistema);
        dibujarEstaciones();
        posicionarEnPane(boton,840/RESX,800/RESGAEL);
        posicionarEnPane(lbTurno,20/RESX,50/RESGAEL);
        posicionarEnPane(lbSalidos,550/RESX,50/RESGAEL);
        posicionarEnPane(lbEnSistema,1000/RESX,50/RESGAEL);
    }

    private void dibujarEstaciones(){
        estaciones=new ArrayList<>();
        ArrayList<Estacion> estacionesModelo=controlador.getEstaciones();
        for (int i=0;i<estacionesModelo.size();i++){
            Estacion estacion=estacionesModelo.get(i);
            EstacionView estacionView=new EstacionView(i+1,estacion.getTamanoFila(),estacion.getDados(),Orientacion.HACIA_ABAJO);
            int indiceFinal=i;
            estacionView.setOnMouseClicked(e->seleccionarEstacion(indiceFinal));
            estaciones.add(estacionView);
            anchorPane.getChildren().add(estacionView);
        }

        salidos=new ColaView(controlador.getSalidos(), Orientacion.HACIA_ABAJO);
        anchorPane.getChildren().add(salidos);

        posicionarEnPane(estaciones.get(0),20/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(1),200/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(2),380/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(3),560/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(4),740/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(5),920/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(6),1100/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(7),1280/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(8),1460/RESX,250/RESGAEL);
        posicionarEnPane(estaciones.get(9),1640/RESX,250/RESGAEL);


        for (int i=1;i<estaciones.size();i++){
            anchorPane.getChildren().add(estaciones.get(i).getColaView());
        }

        posicionarEnPane(estaciones.get(1).getColaView(),100/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(2).getColaView(),280/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(3).getColaView(),460/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(4).getColaView(),640/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(5).getColaView(),820/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(6).getColaView(),1000/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(7).getColaView(),1180/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(8).getColaView(),1360/RESX,500/RESGAEL);
        posicionarEnPane(estaciones.get(9).getColaView(),1540/RESX,500/RESGAEL);
        posicionarEnPane(salidos,1720/RESX,500/RESGAEL);
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
        salidos.dibujarCola(controlador.getSalidos());
        lbTurno.setText(""+controlador.getTurno());
        lbSalidos.setText(""+controlador.getSalidos());
        lbEnSistema.setText(""+controlador.getEnSistema());
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
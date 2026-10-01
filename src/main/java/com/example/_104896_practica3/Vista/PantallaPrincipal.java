package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Controlador.Controlador;
import com.example._104896_practica3.Modelo.Estacion;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;

public class PantallaPrincipal {
    private Controlador controlador;
    private AnchorPane anchorPane;
    private ArrayList<EstacionView> estaciones;
    private ImageButton boton;
    private Label lbTurno;
    private Label lbSalidos;
    private Label lbEnSistema;
    private ColaView salidos;
    private int origenSeleccionado=-1;
    private HBox barraGraficas;
    private ImageButton botonAbrirCerrar;
    private boolean barraAbierta;
    private static final double RESX=1920.0;
    private static final double RESGAEL=1080.0;

    public PantallaPrincipal(Controlador controlador){
        this.controlador=controlador;
        anchorPane=new AnchorPane();
        estaciones=new ArrayList<>();
        construirPantallaJuego();
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

        Font fuente = Font.loadFont(getClass().getResourceAsStream("/fonts/supercell-magic-webfont.ttf"), 30);
        String estiloTexto = "-fx-text-fill: white; "+"-fx-effect: dropshadow(gaussian, black, 3, 1, 0, 0);";
        lbTurno=new Label("Turno  0");
        lbSalidos=new Label("Salidos: 0");
        lbEnSistema=new Label("En sistema: 0");
        lbTurno.setFont(fuente);
        lbSalidos.setFont(fuente);
        lbEnSistema.setFont(fuente);
        lbTurno.setStyle(estiloTexto);
        lbSalidos.setStyle(estiloTexto);
        lbEnSistema.setStyle(estiloTexto);

        anchorPane.getChildren().addAll(background,boton,lbTurno,lbSalidos,lbEnSistema);
        dibujarEstaciones();

        construirBarraGraficas();

        posicionarEnPane(boton,840/RESX,900/RESGAEL);
        posicionarEnPane(lbTurno,120/RESX,50/RESGAEL);
        posicionarEnPane(lbSalidos,1500/RESX,50/RESGAEL);
        posicionarEnPane(lbEnSistema,750/RESX,50/RESGAEL);
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
        if(controlador.getTurno()<20){
            if (origenSeleccionado == -1) {
                origenSeleccionado = indice;
                estaciones.get(indice).setStyle("-fx-border-color:red;-fx-border-width:3;");
            } else {
                controlador.moverDados(origenSeleccionado, indice);
                estaciones.get(origenSeleccionado).setStyle("");
                ArrayList<Estacion> estacionesModelo = controlador.getEstaciones();
                estaciones.get(origenSeleccionado).actualizarCantidadDados(estacionesModelo.get(origenSeleccionado).getDados());
                estaciones.get(indice).actualizarCantidadDados(estacionesModelo.get(indice).getDados());
                origenSeleccionado = -1;
            }
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
        lbTurno.setText("Turno "+controlador.getTurno());
        lbSalidos.setText("Salidos: "+controlador.getSalidos());
        lbEnSistema.setText("En sistema: "+controlador.getEnSistema());
        if (controlador.getTurno()==20){
            anchorPane.getChildren().remove(boton);
        }
    }

    private void posicionarEnPane(Node nodo,double porcentajeX,double porcentajeY){
        nodo.translateXProperty().unbind();
        nodo.translateYProperty().unbind();
        nodo.translateXProperty().bind(anchorPane.widthProperty().multiply(porcentajeX));
        nodo.translateYProperty().bind(anchorPane.heightProperty().multiply(porcentajeY));
    }

    private void construirBarraGraficas(){
        barraGraficas=new HBox();
        barraGraficas.setSpacing(100);
        barraGraficas.setAlignment(Pos.CENTER);
        barraGraficas.setPadding(new Insets(0,60,0,60));
        barraGraficas.prefWidthProperty().bind(anchorPane.widthProperty());
        barraGraficas.setStyle("-fx-background-color:#2b3a4a; -fx-border-color:#d4af37; -fx-border-width:3;");
        ImageButton btnActivity=new ImageButton("/recursos/btnActivity.png","/recursos/btnActivity.png",200,200);
        ImageButton btnThroughput=new ImageButton("/recursos/btnThroughput.png","/recursos/btnThroughput.png",200,200);
        ImageButton btnEnSistema=new ImageButton("/recursos/btnNumberInSystem.png","/recursos/btnNumberInSystem.png",200,200);
        ImageButton btnTimeSistema=new ImageButton("/recursos/btnTimeInSystem.png","/recursos/btnTimeInSystem.png",200,200);
        barraGraficas.getChildren().addAll(btnActivity,btnThroughput,btnEnSistema,btnTimeSistema);

        botonAbrirCerrar=new ImageButton("/recursos/btnFlechaArriba.png","/recursos/btnFlechaArriba.png",60,60);
        botonAbrirCerrar.setOnAction(e->alternarBarra());

        barraAbierta=false;
        anchorPane.getChildren().addAll(botonAbrirCerrar,barraGraficas);
        posicionarEnPane(barraGraficas,0,1075/RESGAEL);
        posicionarEnPane(botonAbrirCerrar,900/RESX,1020/RESGAEL);
    }

    private void alternarBarra(){
        double distancia=barraGraficas.getHeight();
        System.out.println(""+distancia);
        double direccion;
        if (barraAbierta) {
            direccion = distancia;
        } else {
            direccion = -distancia;
        }

        barraGraficas.translateYProperty().unbind();
        botonAbrirCerrar.translateYProperty().unbind();

        TranslateTransition transBarra=new TranslateTransition(Duration.millis(300),barraGraficas);
        transBarra.setByY(direccion);

        TranslateTransition transBoton=new TranslateTransition(Duration.millis(300),botonAbrirCerrar);
        transBoton.setByY(direccion);

        transBarra.play();
        transBoton.play();

        if (barraAbierta){
            botonAbrirCerrar.setImagen("/recursos/btnFlechaArriba.png");
        } else {
            botonAbrirCerrar.setImagen("/recursos/btnFlechaAbajo.png");
        }
        barraAbierta=!barraAbierta;
    }

    public AnchorPane getAnchorPane(){
        return anchorPane;
    }
}
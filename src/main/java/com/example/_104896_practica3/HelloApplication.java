package com.example._104896_practica3;

import com.example._104896_practica3.Controlador.Controlador;
import com.example._104896_practica3.Vista.PantallaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage){
        Controlador controlador=new Controlador();
        PantallaPrincipal pantallaPrincipal=new PantallaPrincipal(controlador);

        stage.setScene(new Scene(pantallaPrincipal.getAnchorPane(),1500,843));
        stage.show();
    }

}
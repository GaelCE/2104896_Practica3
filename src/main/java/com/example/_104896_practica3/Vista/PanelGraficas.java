package com.example._104896_practica3.Vista;

import com.example._104896_practica3.Controlador.Controlador;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PanelGraficas extends AnchorPane {
    private Controlador controlador;
    private BarChart<String,Number> chartThroughput;
    private VBox contenedorThroughput;
    private BarChart<String,Number> chartActivity;
    private VBox contenedorActivity;
    private boolean mostrandoMoved=false;
    private ComboBox<String> comboEstacion;
    private BarChart<String,Number> chartTimeInSystem;
    private VBox contenedorTimeInSystem;
    private BarChart<String,Number> chartNumberInSystem;
    private VBox contenedorNumberInSystem;

    public PanelGraficas(Controlador controlador){
        this.controlador=controlador;
        setPickOnBounds(false);
        contenedorThroughput=construirGraficaThroughput();
        contenedorActivity=construirGraficaActivity();
        contenedorTimeInSystem=construirGraficaTimeSistema();
        contenedorNumberInSystem=construirGraficaNumberInSystem();
        getChildren().addAll(contenedorThroughput,contenedorActivity,contenedorTimeInSystem,contenedorNumberInSystem);
    }

    public void mostrarThroughput(){
        actualizarGraficaThroughput();
        contenedorThroughput.setVisible(true);
    }

    public void mostrarActivity(){
        actualizarGraficaActivity("1");
        contenedorActivity.setVisible(true);
    }

    public void mostrarTimeInSystem(){
        actualizarGraficaTimeSistema();
        contenedorTimeInSystem.setVisible(true);
    }

    public void mostrarNumberInSystem(){
        actualizarGraficaNumberInSystem();
        contenedorNumberInSystem.setVisible(true);
    }

    private VBox construirGraficaThroughput(){
        VBox contenedor=new VBox();
        contenedor.prefWidthProperty().bind(this.widthProperty());
        contenedor.prefHeightProperty().bind(this.heightProperty());
        contenedor.setStyle("-fx-background-color:#2b3a4a;");
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(20);

        CategoryAxis ejeX=new CategoryAxis();
        ejeX.setLabel("Turn");
        NumberAxis ejeY=new NumberAxis();
        ejeY.setLabel("Throughput");

        chartThroughput=new BarChart<>(ejeX,ejeY);
        chartThroughput.setLegendVisible(false);
        chartThroughput.setAnimated(false);
        chartThroughput.prefWidthProperty().bind(contenedor.widthProperty().multiply(0.9));
        chartThroughput.prefHeightProperty().bind(contenedor.heightProperty().multiply(0.8));
        chartThroughput.setStyle("-fx-background-color:transparent;");

        ImageButton btnBack=new ImageButton("/recursos/btnBack.png","/recursos/btnBack.png",150,150);
        btnBack.setOnAction(e->contenedor.setVisible(false));

        contenedor.getChildren().addAll(chartThroughput,btnBack);
        contenedor.setVisible(false);
        return contenedor;
    }

    private void actualizarGraficaThroughput(){
        chartThroughput.getData().clear();
        XYChart.Series<String,Number> serie=new XYChart.Series<>();
        ArrayList<Integer> acumulados=controlador.getEstadisticas().getSalidosAcumulados();
        for(int i=0;i<acumulados.size();i++){
            serie.getData().add(new XYChart.Data<>(""+(i+1),acumulados.get(i)));
        }
        chartThroughput.getData().add(serie);
    }

    private VBox construirGraficaActivity(){
        VBox contenedor=new VBox();
        contenedor.prefWidthProperty().bind(this.widthProperty());
        contenedor.prefHeightProperty().bind(this.heightProperty());
        contenedor.setStyle("-fx-background-color:#2b3a4a;");
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(20);

        CategoryAxis ejeX=new CategoryAxis();
        ejeX.setLabel("Turn");
        NumberAxis ejeY=new NumberAxis();
        ejeY.setLabel("Number");

        chartActivity=new BarChart<>(ejeX,ejeY);
        chartActivity.setLegendVisible(false);
        chartActivity.setAnimated(false);
        chartActivity.prefWidthProperty().bind(contenedor.widthProperty().multiply(0.9));
        chartActivity.prefHeightProperty().bind(contenedor.heightProperty().multiply(0.6));
        chartActivity.setStyle("-fx-background-color:transparent;");

        comboEstacion=new ComboBox<>();
        for(int i=1;i<=10;i++){
            comboEstacion.getItems().add(""+i);
        }
        comboEstacion.getItems().add("all");
        comboEstacion.setValue("1");
        comboEstacion.setOnAction(e->actualizarGraficaActivity(comboEstacion.getValue()));

        Button btnRolled=new Button("Rolled");
        Button btnMoved=new Button("Moved");
        btnRolled.setOnAction(e->{
            mostrandoMoved=false;
            actualizarGraficaActivity(comboEstacion.getValue());
        });
        btnMoved.setOnAction(e->{
            mostrandoMoved=true;
            actualizarGraficaActivity(comboEstacion.getValue());
        });

        HBox controles=new HBox();
        controles.setSpacing(20);
        controles.setAlignment(Pos.CENTER);
        controles.getChildren().addAll(comboEstacion,btnRolled,btnMoved);

        ImageButton btnBack=new ImageButton("/recursos/btnBack.png","/recursos/btnBack.png",150,150);
        btnBack.setOnAction(e->contenedor.setVisible(false));

        contenedor.getChildren().addAll(controles,chartActivity,btnBack);
        contenedor.setVisible(false);
        return contenedor;
    }

    private void actualizarGraficaActivity(String seleccion){
        chartActivity.getData().clear();
        XYChart.Series<String,Number> serie=new XYChart.Series<>();
        if(seleccion.equals("all")){
            ArrayList<Double> datos;
            if(mostrandoMoved){
                datos=controlador.getEstadisticas().getMovedPromedio();
            }else {
                datos=controlador.getEstadisticas().getRolledPromedio();
            }
            for(int i=0;i<datos.size();i++){
                serie.getData().add(new XYChart.Data<>(""+(i+1),datos.get(i)));
            }
        }else{
            int indice=Integer.parseInt(seleccion)-1;
            ArrayList<Integer> datos;
            if(mostrandoMoved){
                datos=controlador.getEstadisticas().getMovedDeEstacion(indice);
            }else{
                datos=controlador.getEstadisticas().getRolledDeEstacion(indice);
            }
            for(int i=0;i<datos.size();i++){
                serie.getData().add(new XYChart.Data<>(""+(i+1),datos.get(i)));
            }
        }
        chartActivity.getData().add(serie);
    }

    private VBox construirGraficaTimeSistema(){
        VBox contenedor=new VBox();
        contenedor.prefWidthProperty().bind(this.widthProperty());
        contenedor.prefHeightProperty().bind(this.heightProperty());
        contenedor.setStyle("-fx-background-color:#2b3a4a;");
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(20);

        CategoryAxis ejeX=new CategoryAxis();
        ejeX.setLabel("Order of arrival");
        NumberAxis ejeY=new NumberAxis();
        ejeY.setLabel("Time in system");

        chartTimeInSystem=new BarChart<>(ejeX,ejeY);
        chartTimeInSystem.setLegendVisible(false);
        chartTimeInSystem.setAnimated(false);
        chartTimeInSystem.prefWidthProperty().bind(contenedor.widthProperty().multiply(0.9));
        chartTimeInSystem.prefHeightProperty().bind(contenedor.heightProperty().multiply(0.8));
        chartTimeInSystem.setStyle("-fx-background-color:transparent;");

        ImageButton btnBack=new ImageButton("/recursos/btnBack.png","/recursos/btnBack.png",150,150);
        btnBack.setOnAction(e->contenedor.setVisible(false));

        contenedor.getChildren().addAll(chartTimeInSystem,btnBack);
        contenedor.setVisible(false);
        return contenedor;
    }

    private void actualizarGraficaTimeSistema(){
        chartTimeInSystem.getData().clear();
        XYChart.Series<String,Number> serie=new XYChart.Series<>();
        ArrayList<Integer> tiempos=controlador.getEstadisticas().getTiemposEnSistema();
        for (int i=0;i<tiempos.size();i++){
            serie.getData().add(new XYChart.Data<>(""+(i+1),tiempos.get(i)));
        }
        chartTimeInSystem.getData().add(serie);
    }

    private VBox construirGraficaNumberInSystem(){
        VBox contenedor=new VBox();
        contenedor.prefWidthProperty().bind(this.widthProperty());
        contenedor.prefHeightProperty().bind(this.heightProperty());
        contenedor.setStyle("-fx-background-color:#2b3a4a;");
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(20);

        CategoryAxis ejeX=new CategoryAxis();
        ejeX.setLabel("Turn");
        NumberAxis ejeY=new NumberAxis();
        ejeY.setLabel("Number in system");

        chartNumberInSystem=new BarChart<>(ejeX,ejeY);
        chartNumberInSystem.setLegendVisible(false);
        chartNumberInSystem.setAnimated(false);
        chartNumberInSystem.prefWidthProperty().bind(contenedor.widthProperty().multiply(0.9));
        chartNumberInSystem.prefHeightProperty().bind(contenedor.heightProperty().multiply(0.8));
        chartNumberInSystem.setStyle("-fx-background-color:transparent;");

        ImageButton btnBack=new ImageButton("/recursos/btnBack.png","/recursos/btnBack.png",150,150);
        btnBack.setOnAction(e->contenedor.setVisible(false));

        contenedor.getChildren().addAll(chartNumberInSystem,btnBack);
        contenedor.setVisible(false);
        return contenedor;
    }

    private void actualizarGraficaNumberInSystem(){
        chartNumberInSystem.getData().clear();
        XYChart.Series<String,Number> serie=new XYChart.Series<>();
        ArrayList<Integer> cantidad=controlador.getEstadisticas().getEnSistemaPorTurno();
        for(int i=0;i<cantidad.size();i++){
            serie.getData().add(new XYChart.Data<>(""+(i+1),cantidad.get(i)));
        }
        chartNumberInSystem.getData().add(serie);
    }
}
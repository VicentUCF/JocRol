package sample;


import game.IniciarJuego;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Main extends Application {

    private IniciarJuego juego = new IniciarJuego(23,34,"prova");


    private Group root;
    private Scene escena;



    @Override
    public void start(Stage ventana) throws Exception{

        root = FXMLLoader.load(getClass().getResource("vista-inicio.fxml"));
        escena = new Scene(root);


        ventana.setScene(escena);
        ventana.setTitle("Fraps");
        ventana.show();
        juego.start();
        cicloJuego();
    }


    public void InicializarComponentes() throws IOException {



    }





    public void cicloJuego(){
        //long tiempo_inicial = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long tiempo_actual) {
                //double t = (tiempo_actual - tiempo_inicial) / 100000000.0;
                juego.getJuego().GenerarGraficos();
            }
        };
        animationTimer.start();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

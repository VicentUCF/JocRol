package sample;

import game.IniciarJuego;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControladorInicio {



    public Button play;

    public void jugar(MouseEvent onClick) {
         IniciarJuego juego = new IniciarJuego(23,34,"prova");
         juego.start();
    }
}

package game;

public class IniciarJuego extends Thread{
    private Game juego;

    public IniciarJuego(int y,int x,String nombrePartida) {
        juego = new Game(x,y,nombrePartida);
    }

    @Override
    public void run(){
        juego.ComenzarJuego();
    }


    public Game getJuego() {
        return juego;
    }
}

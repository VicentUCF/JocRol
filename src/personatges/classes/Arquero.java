package personatges.classes;

import personatges.Bo;
import personatges.interfaces.Distancia;

public class Arquero extends Bo implements Distancia {
    private int rango;

    public Arquero(String textura, String nom) {
        super(textura, nom, 100, 15, 10, 100, 15, 10);
        rango = 5;
    }

    @Override
    public int Rango() {
        return rango;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }
}

package mapa;

import main.Animacio;

import java.io.Serializable;
import java.util.Objects;

public class Casella implements Serializable {
    private Animacio animacio;


    public Casella(Animacio animacio) {
        this.animacio = animacio;
    }

    public Casella() {
        this.animacio = null;
    }

    public Animacio getAnimacio() {
        return animacio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casella casella = (Casella) o;
        return Objects.equals(animacio, casella.animacio);
    }

    @Override
    public String toString() {
        return " ";
    }

    public void setAnimacio(Animacio animacio) {
        this.animacio = animacio;
    }


}

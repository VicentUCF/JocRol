package main;

import java.io.Serializable;

public abstract class Animacio implements Serializable {
    protected String textura;
    protected String nom;

    public Animacio(String textura, String nom) {
        this.textura = textura;
        this.nom = nom;
    }

    public String getTextura() {
        return textura;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

package personatges.classes;

import personatges.Bo;
import personatges.interfaces.Distancia;

public abstract class Brujo extends Bo implements Distancia {
    public Brujo(String textura, String nom) {
        super(textura, nom, 100, 18, 5, 100, 18, 5);
    }


}

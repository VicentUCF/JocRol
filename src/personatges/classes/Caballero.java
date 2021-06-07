package personatges.classes;

import personatges.Bo;
import personatges.interfaces.CosACos;

public class Caballero extends Bo implements CosACos {
    public Caballero(String textura, String nom) {
        super(textura, nom, 200, 5, 50, 120, 5, 50);
    }
}

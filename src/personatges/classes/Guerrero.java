package personatges.classes;

import personatges.Bo;
import personatges.interfaces.CosACos;

public class Guerrero extends Bo implements CosACos {
    public Guerrero(String textura, String nom) {
        super(textura, nom, 100, 18, 20, 100, 18, 20);
    }
}

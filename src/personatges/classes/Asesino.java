package personatges.classes;

import personatges.Bo;
import personatges.interfaces.CosACos;

public class Asesino extends Bo implements CosACos {
    public Asesino(String textura, String nom) {
        super(textura, nom, 80, 20, 10, 80, 20, 10);
    }
}

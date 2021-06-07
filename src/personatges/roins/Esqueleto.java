package personatges.roins;

import personatges.Roin;
import personatges.interfaces.CosACos;
import personatges.interfaces.Distancia;

public class Esqueleto extends Roin implements Distancia {
    public Esqueleto() {
        super("esqueleto", "Esqueleto", 50, 15, 3, 50, 15, 3, 15);
    }
}

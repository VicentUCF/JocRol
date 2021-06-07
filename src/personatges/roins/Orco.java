package personatges.roins;

import personatges.Roin;
import personatges.interfaces.CosACos;

public class Orco extends Roin implements CosACos {
    public Orco() {
        super("orco", "Orco", 100, 30, 20, 100, 30, 20, 30);
    }
}

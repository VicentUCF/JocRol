package artilugis.suplements;

import artilugis.Suplements;

public class Pocio extends Suplements {

    private static int total_pocions = 0;

    public Pocio(String textura, String nom, int tiradas) {
        super(textura, nom, tiradas);
        total_pocions++;
    }

    public Pocio(String textura, String nom, boolean cofre) {
        super(textura, nom, 0);
    }
}

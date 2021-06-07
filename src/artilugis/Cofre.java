package artilugis;

import main.Utilidades;

public class Cofre extends Artilugis {
    private static int TotalCofres = 0;

    public Cofre(String nom, int tiradas) {
        super("cofre", nom, tiradas);
        TotalCofres++;
    }

    public int PuntsCofre(int minim, int maxim) {
        Utilidades u = new Utilidades();
        return u.RandomNum(minim, maxim);
    }

    public static int getTotalCofres() {
        return TotalCofres;
    }

    public static void setTotalCofres(int totalCofres) {
        TotalCofres = totalCofres;
    }
}

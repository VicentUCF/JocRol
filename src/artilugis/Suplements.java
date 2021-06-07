package artilugis;

public abstract class Suplements extends Artilugis {
    protected static int total_pocions = 0;

    public Suplements(String textura, String nom, int tiradas) {
        super(textura, nom, tiradas);
        total_pocions++;
    }

    public static int getTotal_pocions() {
        return total_pocions;
    }

    public static void setTotal_pocions(int total_pocions) {
        Suplements.total_pocions = total_pocions;
    }
}

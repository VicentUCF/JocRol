package artilugis;

public abstract class Arma extends Suplements {

    protected int bonusDany;
    protected static int total_armes = 0;

    //private static int numeroArmes;

    public Arma(String textura, String nom, int tiradas, int bonusDany) {
        super(textura, nom, tiradas);
        this.bonusDany = bonusDany;
        total_armes++;
    }

    public static int getTotal_armes() {
        return total_armes;
    }

    public static void setTotal_armes(int total_armes) {
        Arma.total_armes = total_armes;
    }

    public int getBonusDany() {
        return bonusDany;
    }

    public void setBonusDany(int bonusDany) {
        this.bonusDany = bonusDany;
    }
}
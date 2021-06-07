package artilugis;

import main.Animacio;

public abstract class Artilugis extends Animacio implements Caducable {
    protected static int tiradas = 0;
    protected int salida_tirada;

    public Artilugis(String textura, String nom, int durada) {
        super(textura, nom);
        salida_tirada = tiradas + durada;
    }


    @Override
    public boolean Desaparecer() {
        return (tiradas == salida_tirada);
    }


    public static int getTiradas() {
        return tiradas;
    }

    public static void setTiradas(int tiradas) {
        Artilugis.tiradas = tiradas;
    }


    public int getSalida_tirada() {
        return salida_tirada;
    }

    public void setSalida_tirada(int salida_tirada) {
        this.salida_tirada = salida_tirada;
    }
}

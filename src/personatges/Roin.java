package personatges;

import main.Utilidades;

import java.util.ArrayList;

public class Roin extends Personatge {

    private final int soltarPuntuacio;

    public Roin(String textura, String nom, int vida, int atac, int defensa, int max_vida, int max_atac, int max_defensa, int soltarPuntuacio) {
        super(textura, nom, vida, atac, defensa, max_vida, max_atac, max_defensa);
        this.soltarPuntuacio = soltarPuntuacio;
    }


    @Override
    public boolean VolsAtacar(ArrayList<Personatge> defensors) {
        if(defensors.size()>0){
            Atacar(defensors.get(0));;
            return true;
        }
        return false;
    }


    @Override
    public char Menejar() {
        Utilidades u = new Utilidades();
        int moviment = u.RandomNum(1, 4);

        switch (moviment) {
            case 1:
                return 'w';
            case 2:
                return 's';
            case 3:
                return 'a';
            case 4:
                return 'd';
        }
        return 'n';
    }


    public int Pasos() {
        Utilidades u = new Utilidades();
        return u.RandomNum(1, 5);
    }

    public int getSoltarPuntuacio() {
        return soltarPuntuacio;
    }
}

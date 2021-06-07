package personatges;

import artilugis.Arma;
import main.Animacio;
import main.Utilidades;

import java.util.ArrayList;

public abstract class Personatge extends Animacio {
    protected int vida;
    protected int atac;
    protected int defensa;
    protected int max_vida;
    protected int max_atac;
    protected int max_defensa;


    public Personatge(String textura, String nom, int vida, int atac, int defensa, int max_vida, int max_atac, int max_defensa) {
        super(textura, nom);
        this.vida = vida;
        this.atac = atac;
        this.defensa = defensa;
        this.max_vida = max_vida;
        this.max_atac = max_atac;
        this.max_defensa = max_defensa;
    }

    public abstract char Menejar();

    public void DanyCausat(Personatge defensor , int atac_realitzat){
        System.out.println(getNom() + " ha realitzat un atac de " + atac_realitzat + " a " + defensor.getNom());
        defensor.setVida(defensor.getVida() - atac_realitzat);

        if (defensor.EstaMort()) {
            System.out.println(defensor.getNom() + " ha mort a mans de " + getNom());
        }else if (this instanceof Bo && defensor.EstaMort()) {
            ((Bo)this).SumarPunts(((Roin) defensor).getSoltarPuntuacio());

            //log.AnyadirEvento(atacant.getNom() + "ha matat a " + defensor.getNom());
        }

    }

    public void Atacar(Personatge defensor){

        Utilidades u = new Utilidades();

        Arma arma = null;
        int dany_arma = 0;

        if(this instanceof Bo){
            arma = ((Bo)this).ElegirArma();
        }

        if(arma != null){
            dany_arma = arma.getBonusDany();
        }


        int dany = (this.atac + dany_arma);

        int max_atac = dany - (defensor.getDefensa() * (dany/100));

        int atac_realitzat = u.RandomNum((max_atac / 2), max_atac) ;

        DanyCausat(defensor,atac_realitzat);
    }




    public abstract boolean VolsAtacar(ArrayList<Personatge> defensors);

    public boolean EstaMort(){
        return  vida <= 0;
    }


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtac() {
        return atac;
    }

    public void setAtac(int atac) {
        this.atac = atac;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getMax_vida() {
        return max_vida;
    }

    public int getMax_atac() {
        return max_atac;
    }

    public int getMax_defensa() {
        return max_defensa;
    }

    public abstract int Pasos();
}

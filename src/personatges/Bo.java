package personatges;

import artilugis.Arma;
import artilugis.Artilugis;
import artilugis.Cofre;
import artilugis.Suplements;
import artilugis.armes.ArmaADistancia;
import artilugis.armes.ArmaCosACos;
import artilugis.cofres.CofreComun;
import artilugis.cofres.CofreLegendario;
import artilugis.cofres.CofreOro;
import artilugis.suplements.PocioDefensa;
import artilugis.suplements.PocioVida;
import main.Utilidades;
import personatges.interfaces.CosACos;
import personatges.interfaces.Distancia;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Bo extends Personatge {

    protected ArrayList<Arma> armes;
    protected int puntuacio;

    public Bo(String textura, String nom, int vida, int atac, int defensa, int max_vida, int max_atac, int max_defensa) {
        super(textura, nom, vida, atac, defensa, max_vida, max_atac, max_defensa);
        this.armes = new ArrayList<>();
        this.puntuacio = 0;
    }

    @Override
    public char Menejar() {

        Scanner teclat = new Scanner(System.in);

        char moviment = 'n';

        do {
            System.out.println("W/A/S/D");
            moviment = teclat.next().charAt(0);
        } while (moviment != 'w' && moviment != 'a' && moviment != 's' && moviment != 'd');


        return moviment;
    }

    public int Pasos() {
        Utilidades u = new Utilidades();
        return u.RandomNum(1, 5);
    }

    public void AgafarArma(Arma arma) {

        if (!armes.contains(arma)) {
            armes.add(arma);
            System.out.println("Has agafat " + arma.getNom());
        } else {
            System.out.println("Ja hi tens aquesta arma al teu inventari");
        }

        Arma.setTotal_armes(Arma.getTotal_armes() - 1);
    }

    public ArrayList<Arma> getArmes() {
        return armes;
    }


    public void Agafar_PocioDefensa(PocioDefensa d) {

        int defensa_extra = d.SumarDefensa();
        setDefensa(getDefensa() + defensa_extra);
        System.out.println("Se ha aumentat la armadura " + defensa_extra + " punts| defensa:" + getDefensa());

    }

    public void Agafar_PocioVida(PocioVida v) {

        int vida_pocio = v.SumarVida();
        if (vida_pocio + getVida() < getMax_vida()) {
            setVida(getMax_vida());
            System.out.println("Se ha aumentat la vida " + vida_pocio + " punts |vida:" + getVida());
        } else {
            setVida(getMax_vida());
            System.out.println("Se ha restaurat tota la vida" + "| vida:" + getVida());
        }


    }

    public void AgafarSuplement(Suplements s) {
        if (s instanceof PocioVida) {
            Agafar_PocioVida(((PocioVida) s));
        }

        if (s instanceof PocioDefensa) {
            Agafar_PocioDefensa(((PocioDefensa) s));
        }

        Suplements.setTotal_pocions(Suplements.getTotal_pocions() - 1);

    }


    @Override
    public boolean VolsAtacar(ArrayList<Personatge> defensors) {

        Scanner teclat = new Scanner(System.in);
        char opcio;


        if (defensors.size() >= 1) {

                do {
                    System.out.println("Vols atacar?(s/n)");
                    opcio = teclat.next().charAt(0);
                } while (opcio != 's' && opcio != 'n');

                try {
                    if (opcio == 's') {
                        if (defensors.size() > 1) {
                            int defensor;

                            System.out.println("A que malo vols atacar:");
                            for (Personatge m : defensors) {
                                System.out.println((defensors.indexOf(m) + 1) + ". " + m.getNom() + " Vida: " + m.getVida());
                            }
                            do {
                                defensor = teclat.nextInt();
                            } while (defensor < 1);

                            Atacar(defensors.get(defensor - 1));
                            return true;
                        } else {
                            Atacar(defensors.get(0));
                            return true;
                        }
                    }
                }catch (Exception e){
                    VolsAtacar(defensors);
                }
            }

        return false;
    }


    public Arma ElegirArma(){
        int dany_arma;
        int arma_elegida;
        boolean elegida = false;
        System.out.println("Quina arama vols elegir?");
        int cont = 1;

        if(armes.size()>1){
            for (Arma a : armes) {
                System.out.println(cont + ". " + a.getNom() + " : " + a.getBonusDany() + " d'any extra");
                cont++;
            }
            Scanner teclat = new Scanner(System.in);
            do {
                arma_elegida = teclat.nextInt() -1 ;
                if (arma_elegida >= 0 && arma_elegida <= armes.size()) {
                    elegida = true;
                    return  armes.get(arma_elegida);
                }
            } while (!elegida);
        }else if (armes.size()==1){
            return  armes.get(0);
        }

        return  null;
    }


    public void AgafarArtilugi(Artilugis artilugi) {

        if (artilugi instanceof Arma) {

            if ((artilugi instanceof ArmaADistancia && this instanceof Distancia) || (artilugi instanceof ArmaCosACos && this instanceof CosACos)) {
                AgafarArma((Arma) artilugi);
                System.out.println(artilugi.getNom() + " Anyadida a tu cinturon de armas");
                //log.AnyadirEvento(p.getNom() + " ha recollit un arma");
            } else {
                System.out.println("Aquesta arma no pertany a la teua classe");
            }

        }

        if (artilugi instanceof Suplements) {
            AgafarSuplement(((Suplements) artilugi));

        }

        if (artilugi instanceof Cofre) {
            if (artilugi instanceof CofreComun) {
                System.out.println("Has obert un Cofre Comun");
               //log.AnyadirEvento(p.getNom() + " ha obert un Cofre Comun");
                SumarPunts(((CofreComun) artilugi).PuntsCofre(15, 30));
            }
            if (artilugi instanceof CofreLegendario) {
                System.out.println("Has obert un Cofre Legendario!!");
                SumarPunts(((CofreLegendario) artilugi).PuntsCofre(80, 100));
                AgafarSuplement(((CofreLegendario) artilugi).SoltarSuplement());
                AgafarArma(((CofreLegendario) artilugi).SoltarArma());
               // log.AnyadirEvento(p.getNom() + " ha obert un Cofre Legendario");
            }
            if (artilugi instanceof CofreOro) {
                System.out.println("Has obert un Cofre de Oro");
                SumarPunts(((CofreOro) artilugi).PuntsCofre(80, 100));
                AgafarSuplement(((CofreOro) artilugi).SoltarSuplement());
               // log.AnyadirEvento(p.getNom() + " ha obert un Cofre de Oro");
            }
            Cofre.setTotalCofres(Cofre.getTotalCofres() - 1);
        }

    }



    public void SumarPunts(int punts) {
        setPuntuacio(getPuntuacio() + punts);
        System.out.println(getNom() + " te un total de " + getPuntuacio() + " punts");
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }
}

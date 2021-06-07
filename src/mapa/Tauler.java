package mapa;

import artilugis.Arma;
import artilugis.Artilugis;
import artilugis.Cofre;
import artilugis.Suplements;
import artilugis.cofres.CofreComun;
import artilugis.suplements.PocioDefensa;
import artilugis.suplements.PocioVida;
import main.Utilidades;
import personatges.Bo;
import personatges.Personatge;
import personatges.Roin;

import java.io.Serializable;
import java.util.ArrayList;


public class Tauler implements Serializable {
    private int x;
    private int y;
    private Casella[][] tauler;
    private final Utilidades u = new Utilidades();


    public Tauler(int x, int y) {
        this.x = x;
        this.y = y;
        this.tauler = new Casella[y][x];
        ConstruirTablero();
    }

//    public void MostrarTauler() {
//
//        NetejarConsola();
//
//        for (int i = 0; i < x * 2 - 5; i++) {
//            if (i == x - 3) {
//                System.out.print("FRAPS");
//            } else {
//                System.out.print("_");
//            }
//
//        }
//
//        System.out.println(" ");
//
//        for (int i = 0; i < y; i++) {
//            //System.out.print("|");
//            for (int j = 0; j < x; j++) {
//                System.out.print("_");
//                if (tauler[i][j].getAnimacio() == null) {
//                    System.out.print(tauler[i][j].toString());
//                } else {
//                    System.out.print(tauler[i][j].getAnimacio().getTextura());
//                }
//
//                //System.out.print("|");
//            }
//            System.out.println(" ");
//        }
//
//        for (int i = 0; i < x * 2; i++) {
//            System.out.print("-");
//        }
//        System.out.println(" ");
//    }

    public void ConstruirTablero() {

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                    tauler[i][j] = new Casella();
            }
        }
    }

    public void GenerarMalos(int nroins) {

        int cont = 0;

        do {
            int posx = u.RandomNum(0, x);
            int posy = u.RandomNum(0, y);
            if (tauler[posy][posx].getAnimacio() == null) {
                tauler[posy][posx] = new Casella(u.RoinAleatori());
                cont++;
            }
        } while (cont != nroins);
    }

    public ArrayList<Roin> BuscarMalos() {

        ArrayList<Roin> malos = new ArrayList();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (tauler[i][j].getAnimacio() instanceof Roin) {
                    malos.add(((Roin) tauler[i][j].getAnimacio()));
                }
            }
        }

        return malos;
    }


    public void GenerarBons(Personatge personatge) {

        boolean colocat = false;
        do {
            int posx = u.RandomNum(0, 3);
            int posy = u.RandomNum(0, y);
            if (tauler[posy][posx].getAnimacio() == null) {
                tauler[posy][posx] = new Casella(personatge);
                colocat = true;
            }
        } while (!colocat);
    }

    public void GenerarCofre() {
        if (Cofre.getTotalCofres() <= 2) {
            boolean arma_generada = false;

            do {
                int posx = u.RandomNum(0, x);
                int posy = u.RandomNum(0, y);

                if (tauler[posy][posx].getAnimacio() == null) {
                    tauler[posy][posx] = new Casella(u.CofreAleatori());
                    arma_generada = true;
                }
            } while (!arma_generada);
        }
    }

    public void GenerarArma() {

        if (Arma.getTotal_armes() <= 3) {

            boolean arma_generada = false;

            do {
                int posx = u.RandomNum(0, x);
                int posy = u.RandomNum(0, y);

                if (tauler[posy][posx].getAnimacio() == null) {
                    tauler[posy][posx] = new Casella(u.ArmaAleatoria());
                    arma_generada = true;
                }
            } while (!arma_generada);
        }
    }


    public void GenerarPocio(Suplements s) {
        if (Suplements.getTotal_pocions() < 5) {

            boolean pocio_generada = false;

            do {
                int posx = u.RandomNum(0, x);
                int posy = u.RandomNum(0, y);

                if (tauler[posy][posx].getAnimacio() == null) {
                    tauler[posy][posx] = new Casella(s);
                    pocio_generada = true;
                }
            } while (!pocio_generada);

        }
    }


    public boolean poscorrecta(int posy, int posx) {

        return posy < y && posx < x && posy >= 0 && posx >= 0;
    }//Cierre del método

    public Artilugis ObjecteTrobat(int y, int x) {

        if (poscorrecta(y - 1, x) && tauler[y - 1][x].getAnimacio() instanceof Artilugis) {
            Artilugis artilugi = (Artilugis) tauler[y - 1][x].getAnimacio();
            tauler[y - 1][x] = new Casella();
            return artilugi;
        }
        if (poscorrecta(y, x - 1) && tauler[y][x - 1].getAnimacio() instanceof Artilugis) {
            Artilugis artilugi = (Artilugis) tauler[y][x - 1].getAnimacio();
            tauler[y][x - 1] = new Casella();
            return artilugi;
        }
        if (poscorrecta(y + 1, x) && tauler[y + 1][x].getAnimacio() instanceof Artilugis) {
            Artilugis artilugi = (Artilugis) tauler[y + 1][x].getAnimacio();
            tauler[y + 1][x] = new Casella();
            return artilugi;
        }
        if (poscorrecta(y, x + 1) && tauler[y][x + 1].getAnimacio() instanceof Artilugis) {
            Artilugis artilugi = (Artilugis) tauler[y][x + 1].getAnimacio();
            tauler[y][x + 1] = new Casella();
            return artilugi;
        }

        return null;
    }

    public void NetejarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void BorrarArtilugis() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (tauler[i][j].getAnimacio() instanceof Artilugis) {
                    if (((Artilugis) tauler[i][j].getAnimacio()).Desaparecer()) {

                        if (tauler[i][j].getAnimacio() instanceof Suplements) {
                            Suplements.setTotal_pocions(Suplements.getTotal_pocions() - 1);
                        }

                        if (tauler[i][j].getAnimacio() instanceof Arma) {
                            Arma.setTotal_armes(Arma.getTotal_armes() - 1);
                        }

                        if (tauler[i][j].getAnimacio() instanceof Cofre) {
                            Cofre.setTotalCofres(Cofre.getTotalCofres() - 1);
                            if (tauler[i][j].getAnimacio() instanceof CofreComun) {

                            } else {
                                Suplements.setTotal_pocions(Suplements.getTotal_pocions() - 1);
                            }
                        }

                        tauler[i][j] = new Casella();
                    }
                }
            }
        }
    }


    public void GenerarArtilugiAleatori() {

        int artilugi = u.RandomNum(1, 6);

        switch (artilugi) {
            case 1:
                GenerarArma();
                break;
            case 2:
                GenerarPocio(new PocioVida());
                break;
            case 3:
                GenerarPocio(new PocioDefensa());
                break;
            case 4:
                GenerarCofre();
            default:
                break;
        }

    }


    public boolean PotMenejarse(int y, int x, char moviment) {
        switch (moviment) {
            case 'w' -> {
                if (poscorrecta(y - 1, x) && tauler[y - 1][x].getAnimacio() == null) {
                    return true;
                }
            }
            case 'a' -> {
                if (poscorrecta(y, x - 1) && tauler[y][x - 1].getAnimacio() == null) {
                    return true;
                }
            }
            case 's' -> {
                if (poscorrecta(y + 1, x) && tauler[y + 1][x].getAnimacio() == null) {
                    return true;
                }
            }
            case 'd' -> {
                if (poscorrecta(y, x + 1) && tauler[y][x + 1].getAnimacio() == null) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean EsUnEnemig(Personatge p, int posy, int posx) {

        if (poscorrecta(posy, posx)) {
            if (tauler[posy][posx].getAnimacio() instanceof Bo && p instanceof Roin) {
                return true;
            } else return tauler[posy][posx].getAnimacio() instanceof Roin && p instanceof Bo;
        }

        return false;
    }


    public void BorrarPersonatgesMorts(){

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (tauler[i][j].getAnimacio() instanceof  Personatge) {
                    if (((Personatge)tauler[i][j].getAnimacio()).EstaMort()) {
                        tauler[i][j] = new Casella();
                    }
                }
            }
        }
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Casella[][] getTauler() {
        return tauler;
    }

    public void setTauler(Casella[][] tauler) {
        this.tauler = tauler;
    }
}

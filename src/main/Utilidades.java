package main;

import artilugis.Arma;
import artilugis.Cofre;
import artilugis.armes.*;
import artilugis.cofres.CofreComun;
import artilugis.cofres.CofreLegendario;
import artilugis.cofres.CofreOro;
import mapa.Obstacles;
import mapa.entorno.*;
import personatges.Personatge;
import personatges.roins.*;

import java.io.Serializable;

public class Utilidades implements Serializable {


    public Cofre CofreAleatori() {
        int Cofre = RandomNum(1, 6);
        return switch (Cofre) {
            case 1 -> new CofreComun();
            case 4 -> new CofreComun();
            case 5 -> new CofreComun();
            case 2 -> new CofreOro();
            case 6 -> new CofreOro();
            case 3 -> new CofreLegendario();
            default -> null;
        };
    }

    public Arma ArmaAleatoria() {
        int arma = RandomNum(1, 8);

        return switch (arma) {
            case 1 -> new daga();
            //case 2 -> new ArcoCurvo();
            case 3 -> new Espada();
            case 4 -> new EspadaGuerra();
            case 5 -> new EspadaOxidada();
            case 6 -> new HachaGuerra();
            case 7 -> new Maza();
            case 8 -> new MazaGuerra();
            default -> null;
        };

    }

    public Obstacles TerraAleatori(){
        int terra = RandomNum(1,5);
        return switch (terra){
            case 1 -> new Tierra1();
            case 2 -> new Tierra2();
            case 3 -> new Tierra3();
            case 4 -> new Tierra4();
            case 5 -> new Tierra5();
            default -> null;
        };
    }


    public Personatge RoinAleatori() {
        int malo = RandomNum(1, 6);

        return switch (malo) {
            case 1 -> new Esqueleto();
            case 2 -> new Orco();
            case 3 -> new MagoOscuro();
            case 4 -> new MiniDiablo();
            case 5 -> new MiniGuardian();
            case 6 -> new MiniOrco();
            default -> null;
        };

    }


    public int RandomNum(int menor, int mayor) {
        return (int) (Math.random() * mayor + menor);
    }
}

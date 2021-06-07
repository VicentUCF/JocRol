package artilugis.cofres;

import artilugis.Cofre;
import artilugis.Suplements;
import artilugis.suplements.PocioDefensa;
import artilugis.suplements.PocioVida;
import main.Utilidades;

public class CofreOro extends Cofre {
    public CofreOro() {
        super( "CofreOro", 10);
    }

    public Suplements SoltarSuplement() {
        Utilidades u = new Utilidades();

        int suplement = u.RandomNum(1, 4);

        switch (suplement) {
            case 1 -> {
                return (new PocioVida(true));
            }
            case 2 -> {
                return (new PocioDefensa(true));
            }
            default -> {
                return null;
            }
        }
    }

}

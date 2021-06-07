package artilugis.cofres;

import artilugis.Arma;
import artilugis.Cofre;
import artilugis.Suplements;
import artilugis.suplements.PocioDefensa;
import artilugis.suplements.PocioVida;
import main.Utilidades;

public class CofreLegendario extends Cofre {
    public CofreLegendario() {
        super("Cofre Legendario", 5);
    }

    public Suplements SoltarSuplement() {
        Utilidades u = new Utilidades();

        int suplement = u.RandomNum(1, 2);

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

    public Arma SoltarArma() {
        Utilidades u = new Utilidades();
        return u.ArmaAleatoria();
    }


}

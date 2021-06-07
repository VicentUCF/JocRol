package artilugis.suplements;

import main.Utilidades;

public class PocioVida extends Pocio {

    public PocioVida() {
        super("pociovida", "Pocio Vida", 15);
    }

    //Si la Pocio es de cofre no conta per el total
    public PocioVida(boolean esta_en_un_cofre) {
        super("v", "Pocio Vida", tiradas);
    }


    public int SumarVida() {
        Utilidades u = new Utilidades();
        return u.RandomNum(10, 50);
    }


}

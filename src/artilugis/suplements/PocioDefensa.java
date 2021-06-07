package artilugis.suplements;

import main.Utilidades;

public class PocioDefensa extends Pocio {
    public PocioDefensa() {
        super("pociodefensa", "Pocio Defensa", 10);
    }


    //Si la Pocio es de cofre no conta per el total
    public PocioDefensa(boolean esta_en_un_cofre) {
        super("d", "Pocio Defensa", esta_en_un_cofre);
    }


    public int SumarDefensa() {
        Utilidades u = new Utilidades();
        return u.RandomNum(5, 10);
    }


}

package personatges.roins;

import personatges.Roin;
import personatges.interfaces.CosACos;

public class MiniGuardian extends Roin implements CosACos {
    public MiniGuardian(){
        super("miniguardian","MiniGuardian",100,15,10,100,15,10,30);
    }
}

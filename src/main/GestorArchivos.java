package main;

import game.Usuario;
import mapa.Tauler;

import javax.management.ObjectName;
import java.io.*;
import java.util.ArrayList;

public class GestorArchivos{

    public void GuardarPartida(String nompartida,Tauler tauler, ArrayList<Usuario> usuarios){

        Guardar(new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/"+nompartida+"_tauler"),tauler);
        Guardar(new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/"+nompartida+"_usuaris"),usuarios);

        System.out.println("s'ha guardat la partida");
    }

    public void CargarPartida(String nompartida){
        Cargar(new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/"+nompartida+"_tauler"));
        Cargar(new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/"+nompartida+"_usuaris"));
    }


    public Object Cargar(File f) {

        try {
            FileInputStream fis = new FileInputStream(f.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);

            return ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Partida No encontrada");
        }
        return null;
    }//Cierre del método


    /**
     * Método que Guarda la partida pasantli un String del nom del fitxer
     */
    public void Guardar(File f, Object o) {

        f.mkdirs();

        try {
            FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }//Cierre del método


    /**
     * Método que Borra la partida pasantlli un String del nom de la partida
     */
    public void BorrarPartida(String nompartida,Tauler tauler,ArrayList<Usuario> usuarios) {

        File f_usuarios = new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/" + nompartida + "_usuarios");
        File f_tauler = new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/" + nompartida + "_tauler");

        if (f_usuarios.exists() && f_tauler.exists()) {
            f_usuarios.delete();
            f_tauler.delete();
            System.out.println("Partida Borrada Con exito");
        } else {
            System.out.println("No se ha encontrado la partida");
        }

    }//Cierre del método


}

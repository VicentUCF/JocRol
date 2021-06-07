package game;

import personatges.Personatge;

import java.io.*;
import java.util.Objects;

public class Usuario implements Serializable {

    private String usuario;
    private String pass;
    private Personatge personatge;

    public Usuario(String usuario, String pass, Personatge personatge) {
        this.usuario = usuario;
        this.pass = pass;
        this.personatge = personatge;
    }

    public Usuario(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario);
    }



    public Boolean ComprobarClave(){

        File f_usuarios = new File("/home/vicent_ucf/RolGameSaves/Users");

        try {
            FileInputStream fis = new FileInputStream(f_usuarios.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);

            String clave = (String) ois.readObject();

//            if(clave = ){
//
//                System.out.println("Has iniciado sesion con exito!!");
//                return true;
//            }



        } catch (FileNotFoundException e) {
            f_usuarios.mkdirs();
            //Fer peticio servidor mysql
            ComprobarClave();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Personatge getPersonatge() {
        return personatge;
    }

    public void setPersonatge(Personatge personatge) {
        this.personatge = personatge;
    }
}

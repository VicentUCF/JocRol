package main;

import java.io.Serializable;
import java.util.ArrayList;

public class LogPartida implements Serializable {
    private String nom_partida;
    private ArrayList<String> log;


    public LogPartida(String nom_partida) {
        this.nom_partida = nom_partida;
        this.log = new ArrayList();
    }

    public void AnyadirEvento(String evento) {
        log.add(evento);
    }

    public void LlistarLog() {
        for (String s : log) {
            System.out.println(s);
        }
    }

    public String getNom_partida() {
        return nom_partida;
    }

    public void setNom_partida(String nom_partida) {
        this.nom_partida = nom_partida;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void setLog(ArrayList<String> log) {
        this.log = log;
    }


}

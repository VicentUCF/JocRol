package mapa;

import main.GestorArchivos;
import mapa.entorno.Stop;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneradorMapas  {
    private String nombreMapa;
    private Tauler tauler;
    private ArrayList<Obstacles> materials = new ArrayList<>();
    private  int posy;
    private  int posx;
    private final GestorArchivos g = new GestorArchivos();


    public GeneradorMapas(String nombreMapa,int tamany_vertical, int tamany_horizontal , int posinicial_y , int posinicial_x) {
        this.nombreMapa = nombreMapa;
        this.posy = posinicial_y;
        this.posx = posinicial_x;
        this.tauler = new Tauler(tamany_vertical,tamany_horizontal);
    }

    public void CarregarMaterials(){
        materials.add(new Stop());
    }

    public Obstacles ElegirMaterial(){
        Scanner s = new Scanner(System.in);

        int material;
        do {
            for (Obstacles m : materials) {
                System.out.println(m.getNom() + " " + m.getTextura());
            }
            material = s.nextInt()-1;
        }while (material < 0 || material > materials.size());

        return  materials.get(material);

    }

    public void CrearMapa(){

        if(g.Cargar(new File("/home/vicent_ucf/MapasCustomRol/" + nombreMapa + "/" + nombreMapa + "_world") )!=null){
            tauler = (Tauler)g.Cargar(new File("/home/vicent_ucf/MapasCustomRol/" + nombreMapa + "/" + nombreMapa + "_world"));
        }else{
            for (int i = 0; i < tauler.getY() ; i++) {
                for (int j = 0; j < tauler.getX(); j++) {
                    //tauler.getTauler()[i][j] = new Casella(new Vacio());
                }
            }
        }

        Scanner s = new Scanner(System.in);

        Obstacles material_construccio = new Stop();
        CarregarMaterials();

        //tauler.MostrarTauler();

        do{

            System.out.println("direccio");
            char direccio = s.next().charAt(0);

            System.out.println("pasos");
            int pasos = s.nextInt();

            Menejar(pasos,direccio,ElegirMaterial());

            g.Guardar(new File("/home/vicent_ucf/MapasCustomRol/RolGameSaves/" + nombreMapa),tauler);


        }while (!material_construccio.equals(new Stop()));
    }




        public void Menejar(int pasos, char moviment, Obstacles o) {
        int contPasos = 0;

            do {
                    switch (moviment) {
                        case 'w' -> {

                            if(tauler.poscorrecta(posy-1,posx)){
                                tauler.getTauler()[posy - 1][posx] = new Casella(o);
                                posy--;
                                contPasos++;
                            }
                        }
                        case 'a' -> {
                            if(tauler.poscorrecta(posy,posx+1)){
                                tauler.getTauler()[posy][posx - 1] = new Casella(o);
                                posx--;
                                contPasos++;
                            }
                        }
                        case 's' -> {
                            if(tauler.poscorrecta(posy+1,posx)){
                                tauler.getTauler()[posy + 1][posx] = new Casella(o);
                                posy++;
                                contPasos++;
                            }
                        }
                        case 'd' -> {
                            if(tauler.poscorrecta(posy,posx+1)){
                                tauler.getTauler()[posy][posx + 1] = new Casella(o);
                                posx++;
                                contPasos++;
                            }
                        }
                    }

            } while (contPasos < pasos);

            //tauler.MostrarTauler();
    }//Cierre del método







}

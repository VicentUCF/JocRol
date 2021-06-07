package game;


import artilugis.Artilugis;
import main.GestorArchivos;
import main.LogPartida;
import main.Utilidades;
import mapa.Casella;
import mapa.Tauler;
import personatges.Bo;
import personatges.Personatge;
import personatges.Roin;
import personatges.classes.Asesino;
import personatges.classes.Caballero;
import personatges.classes.Guerrero;
import personatges.interfaces.Distancia;
import personatges.interfaces.CosACos;

import java.io.File;
import java.util.ArrayList;


/**
 * Classe Principal del joc en la que ocurrixen la majoria de funcions se encarrega de gestionar tota la partida
 *
 * @author: Vicent Ciscar Alminana
 * @version: 6/05/2021/A
 */
public class Game {
    private Tauler fondo;
    private Tauler tauler;
    private ArrayList<Usuario> usuarios;
    private final LogPartida log;
    private final GestorArchivos g = new GestorArchivos();
    private final GestorGraficos graficos = new GestorGraficos();

    public Game(int x, int y, String nom_partida) {
        this.tauler = new Tauler(x, y);
        this.fondo = new Tauler(x,y);
        this.usuarios = new ArrayList<>();
        this.log = new LogPartida(nom_partida);
        ConstruirFondo();
    }//Cierre del constructor


    public void ConstruirFondo(){
        Utilidades u = new Utilidades();
        for (int i = 0; i < fondo.getY(); i++) {
            for (int j = 0; j < fondo.getX(); j++) {
                fondo.getTauler()[i][j] = new Casella(u.TerraAleatori());
            }
        }
    }

    //Pinta el fondo que no interactua en les entitats seria el encarregat en un futur de carregar un mapa
    public void PintarFondo(){

        for (int i = 0; i < tauler.getY(); i++) {
            for (int j = 0; j < tauler.getX(); j++) {
                String textura = fondo.getTauler()[i][j].getAnimacio().getTextura();
                graficos.Pintar(textura,i,j);
            }
        }
    }

    //Pinta totes les Animacions que es troben al Tauler
    public void PintarAnimacions(){
        for (int i = 0; i < tauler.getY(); i++) {
            for (int j = 0; j < tauler.getX(); j++) {
                if (tauler.getTauler()[i][j].getAnimacio() != null) {
                    String textura = tauler.getTauler()[i][j].getAnimacio().getTextura();
                    graficos.Pintar(textura, i, j);
                }
            }
        }
    }

    //Pinta en el canvas el fondo del mon i les animacions
    public void GenerarGraficos(){
            PintarFondo();
            PintarAnimacions();
    }


    /**
     * Método que se encarrega de gestionar tota la partida
     */
    public void ComenzarJuego() {

        //if (!CargarPartida("")) {
            tauler.ConstruirTablero();
            Crearusuario("paco", "123", new Caballero("caballero1", "Rogelio El Humano"));
            //Crearusuario("maria","123",new Guerrero("caballero2","elfo Oscuro"));
            //Crearusuario("Vicent", "123", new Asesino("caballero1", "Guerrero Oscuro"));
            tauler.GenerarMalos(8);
        //}

        boolean finjuego = false;

        ArrayList<Roin> malos = null;

        do {
            int personatges_vius = usuarios.size();
            for (Usuario a : usuarios) {
                malos = tauler.BuscarMalos();
                if (malos.size() == 0) {
                    finjuego = true;
                    System.out.println("You Win!!");
                } else {
                    if (a.getPersonatge().getVida() > 0) {
                        TurnoUsuario(a);
                        tauler.BorrarArtilugis();

                    } else {
                        personatges_vius--;
                    }
                }
            }
            if (personatges_vius == 0) {
                finjuego = true;
                System.out.println("Mision Failed");
            } else {
                for (Roin r : malos) {
                    TurnoMaquina(r);
                }

                tauler.GenerarArtilugiAleatori();

                System.out.println();
            }

            //g.GuardarPartida("prova",tauler,usuarios);

        } while (!finjuego);

    }//Cierre del método


    /**
     * Método el qual busca el personatge de un usuari en el tauler
     */
    public void TurnoUsuario(Usuario usuario) {

        if (usuarios.contains(usuario)) {
            Personatge personatge = usuario.getPersonatge();

            System.out.println("Turno de " + personatge.getNom());
            graficos.MostrarTurno("h");
            int posy = 0;
            int posx = 0;


            for (int i = 0; i < tauler.getY(); i++) {
                for (int j = 0; j < tauler.getX(); j++) {
                    if (tauler.getTauler()[i][j].equals(new Casella(personatge))) {
                        posy = i;
                        posx = j;
                    }
                }
            }

            MenejarPersonatge(personatge, posy, posx);



            Artilugis.setTiradas(Artilugis.getTiradas() + 1);
        }

    }//Cierre del método


    /**
     * Método en construccio deuria de recorrer unes caselles en busca de un enemic i en el cas de que el trobe pegarli
     */
    public Personatge Disparar(Personatge p, int posy, int posx, char direccio, int rango) {

        int recorregut = 0;
        boolean menejantse = true;
        do {
            if (tauler.PotMenejarse(posy, posx, direccio)) {
                switch (direccio) {
                    case 'w' -> {
                        posy--;
                        if (EsUnEnemig(p, posy - 1, posx)) {
                            return (Personatge) tauler.getTauler()[posy - 1][posx].getAnimacio();
                        } else {
                            recorregut++;
                        }
                    }

                    case 'a' -> {
                        posx--;
                        if (EsUnEnemig(p, posy, posx - 1)) {
                            return (Personatge) tauler.getTauler()[posy][posx - 1].getAnimacio();
                        } else {
                            recorregut++;
                        }
                    }

                    case 's' -> {
                        posy++;
                        if (EsUnEnemig(p, posy + 1, posx)) {
                            return (Personatge) tauler.getTauler()[posy + 1][posx].getAnimacio();
                        } else {
                            recorregut++;
                        }
                    }

                    case 'd' -> {
                        posx++;
                        if (EsUnEnemig(p, posy, posx + 1)) {
                            return (Personatge) tauler.getTauler()[posy][posx + 1].getAnimacio();
                        } else {
                            recorregut++;
                        }
                    }
                }
            } else {
                menejantse = false;
            }

        } while (recorregut < rango && menejantse);

        return null;

    }//Cierre del método

    /**
     * Método el qual meneja el personatge de un usuari
     */
    public void MenejarPersonatge(Personatge p, int posy, int posx) {
        int contPasos = 0;
        boolean menejantse = true;
        int pasos;
        char moviment;

        if((p instanceof CosACos && !PotAtacarCosaCos(p,posy,posx)) || (p instanceof Distancia && !PotAtacarADistancia(p,posy,posx,((Distancia)p).Rango())) ) {

                pasos = p.Pasos();
                moviment = p.Menejar();

            do {
                if (tauler.PotMenejarse(posy, posx, moviment)) {

                    switch (moviment) {
                        case 'w' -> {
                            tauler.getTauler()[posy - 1][posx] = tauler.getTauler()[posy][posx];
                            tauler.getTauler()[posy][posx] = new Casella();
                            posy--;
                            contPasos++;
                        }
                        case 'a' -> {
                            tauler.getTauler()[posy][posx - 1] = tauler.getTauler()[posy][posx];
                            tauler.getTauler()[posy][posx] = new Casella();
                            posx--;
                            contPasos++;
                        }
                        case 's' -> {
                            tauler.getTauler()[posy + 1][posx] = tauler.getTauler()[posy][posx];
                            tauler.getTauler()[posy][posx] = new Casella();
                            posy++;
                            contPasos++;
                        }
                        case 'd' -> {
                            tauler.getTauler()[posy][posx + 1] = tauler.getTauler()[posy][posx];
                            tauler.getTauler()[posy][posx] = new Casella();
                            posx++;
                            contPasos++;
                        }
                    }
                } else {
                    menejantse = false;
                }


            } while (contPasos < pasos && menejantse);

            //GenerarGraficos();


            if (p instanceof Bo) {
                ((Bo) p).AgafarArtilugi(tauler.ObjecteTrobat(posy, posx));
            }

            if (p instanceof Distancia) {
                PotAtacarADistancia(p, posy, posx, ((Distancia) p).Rango());
            } else {
                PotAtacarCosaCos(p, posy, posx);
            }

        }
        tauler.BorrarPersonatgesMorts();
    }//Cierre del método

    public boolean PotAtacarADistancia(Personatge p, int posy, int posx, int rango) {

        ArrayList<Personatge> posiblesDefensors = new ArrayList<>();

        Personatge w = Disparar(p, posy, posx, 'w', rango);
        Personatge s = Disparar(p, posy, posx, 's', rango);
        Personatge d = Disparar(p, posy, posx, 'd', rango);
        Personatge a = Disparar(p, posy, posx, 'a', rango);

        if (w != null) {
            posiblesDefensors.add(w);
        }
        if (s != null) {
            posiblesDefensors.add(s);
        }
        if (d != null) {
            posiblesDefensors.add(d);
        }
        if (a != null) {
            posiblesDefensors.add(a);
        }

        return p.VolsAtacar(posiblesDefensors);

    }

    public boolean PotAtacarCosaCos(Personatge p, int posy, int posx) {

        ArrayList<Personatge> malos = new ArrayList<>();

        if (tauler.EsUnEnemig(p, posy - 1, posx - 1)) {
            malos.add((Personatge) tauler.getTauler()[posy - 1][posx - 1].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy + 1, posx + 1)) {
            malos.add((Personatge) tauler.getTauler()[posy + 1][posx + 1].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy + 1, posx - 1)) {
            malos.add((Personatge) tauler.getTauler()[posy + 1][posx - 1].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy - 1, posx + 1)) {
            malos.add((Personatge) tauler.getTauler()[posy - 1][posx + 1].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy - 1, posx)) {
            malos.add((Personatge) tauler.getTauler()[posy - 1][posx].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy + 1, posx)) {
            malos.add((Personatge) tauler.getTauler()[posy + 1][posx].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy, posx + 1)) {
            malos.add((Personatge) tauler.getTauler()[posy][posx + 1].getAnimacio());
        }
        if (tauler.EsUnEnemig(p, posy, posx - 1)) {
            malos.add((Personatge) tauler.getTauler()[posy][posx - 1].getAnimacio());
        }

        return  p.VolsAtacar(malos);

    }

    /**
     * Método que comproba si un personatge hi pot atacar cos a cos
     *
     * @return personatge al que pot atacar
     */
    public boolean EsUnEnemig(Personatge defensor, int posy, int posx) {

        if (tauler.poscorrecta(posy, posx)&&tauler.getTauler()[posy][posx].getAnimacio() instanceof Personatge) {
            Personatge atacant = ((Personatge)tauler.getTauler()[posy][posx].getAnimacio());
            return ((atacant instanceof Bo && defensor instanceof Roin) || (atacant instanceof Roin && defensor instanceof Bo));
        }
        return false;
    }


    /**
     * Método que crea un usuari
     *
     * @param nom del usuari,contrasenya del usuari,Un personatge bo
     */
    public void Crearusuario(String nom, String pass, Bo personatge) {
        usuarios.add(new Usuario(nom, pass, personatge));
        log.AnyadirEvento("Usuraio " + nom + " Creado" + " Personaje " + personatge.getNom());
        tauler.GenerarBons(personatge);
    }//Cierre del método

    /**
     * Método que meneja un personatge roin
     *
     * @param malo;
     */
    public void TurnoMaquina(Roin malo) {

        int posy = 0;
        int posx = 0;

        for (int i = 0; i < tauler.getY(); i++) {
            for (int j = 0; j < tauler.getX(); j++) {
                if (tauler.getTauler()[i][j].equals(new Casella(malo))) {
                    posy = i;
                    posx = j;
                }
            }
        }

        MenejarPersonatge(malo, posy, posx);


    }//Cierre del método


    public boolean CargarPartida(String nompartida){

        Tauler t;
        ArrayList<Usuario> u;

        t = (Tauler)g.Cargar(new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/"+nompartida+"_tauler"));
        u = (ArrayList<Usuario>) g.Cargar(new File("/home/vicent_ucf/RolGameSaves/" + nompartida + "/"+nompartida+"_usuaris"));

        if(t!=null && u!= null){
            tauler = t;
            usuarios = u;
            return true;
        }

        return false;

    }

    public GestorGraficos getGraficos() {
        return graficos;
    }


}

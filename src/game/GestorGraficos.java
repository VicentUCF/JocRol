package game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class GestorGraficos {

    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas lienzo;
    private HashMap<String, Image> imagenes;

    public GestorGraficos() {
        InicializarComponentes();
    }

    public void MostrarTurno(String turno){
        //graficos.setStroke(Color.BLACK);
        //graficos.fillText("Turno de proba" ,0,0);
    }


    public void InicializarComponentes(){
        root = new Group();
        escena = new Scene(root,1080,735);
        lienzo = new Canvas(1080,735);
        root.getChildren().add(lienzo);
        graficos = lienzo.getGraphicsContext2D();
        imagenes = new HashMap<>();
        CargarImagenes();
    }

    public void Pintar(String imagen, int posy,int posx){

        try{
            Image animacio = imagenes.get(imagen);
            graficos.drawImage(animacio,posx*32,posy*32);
        }catch (Exception e){
            System.out.println("No se ha encontrado la imagen" + imagen);
        }

    }

    public void CargarImagen(String nom,String ruta){
        imagenes.put(nom,new Image(ruta));
    }

    public void CargarImagen(String nom,String ruta,int tamany){
        try {
            imagenes.put(nom, new Image(ruta, tamany, tamany, false, false));
        }catch (Exception e){
            System.out.println("No se ha pogut carregar la image" + nom);
        }
    }

    public void CargarImagen(String nom,String ruta,int tamany_x,int tamany_y){
        imagenes.put(nom,new Image(ruta,tamany_x,tamany_y,false,false));
    }

    public void CargarImagenes(){

            int tamany = 32;
            //Personatges Bons
            CargarImagen("caballero1","Caballero1.png",tamany);
            CargarImagen("caballero2","Caballero2.png",tamany);
            CargarImagen("humano", "Humano.png",tamany);
            //Final Personatges Bons


            //Terra
            CargarImagen("tierra1","floor1.png",32);
            CargarImagen("tierra2","floor2.png",32);
            CargarImagen("tierra3","floor3.png",32);
            CargarImagen("tierra4","floor4.png",32);
            CargarImagen("tierra5","floor5.png",32);
            //Final terra

            //Personatges Roins
            CargarImagen("orco","Orco.png",tamany);
            CargarImagen("esqueleto","MiniEsqueleto.png",tamany);
            CargarImagen("minidiablo","MiniDiablo.png",tamany);
            CargarImagen("miniguardian","MiniGuardian.png",tamany);
            CargarImagen("miniorco","MiniOrco.png",tamany);
            CargarImagen("magooscuro","MagoMaligno.png",tamany);


            //Final Personatges Roins

            //Suplements
            CargarImagen("pociovida","PocionVida.png",tamany);
            CargarImagen("pociodefensa","PocionDefensa.png",tamany);
            CargarImagen("cofre","Cofre.png",tamany);
            //Final Suplements

            //Armas
            CargarImagen("daga","Daga.png",tamany);
            CargarImagen("espada","Espada.png",tamany);
            CargarImagen("espadaguerra","EspadaGuerra.png",tamany);
            CargarImagen("espadaoxidada","EspadaOxidada.png",tamany);
            CargarImagen("hachaguerra","HachaGuerra.png",tamany);
            CargarImagen("maza","MazaGuerra.png",tamany);
            CargarImagen("mazaguerra","MazaGuerra.png",tamany);

    }

    public void gestionEventos(){

        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evento) {
                System.out.println("Se presiono la tecla" + evento.getCode());
            }
        });
    }

    public GraphicsContext getGraficos() {
        return graficos;
    }

    public Group getRoot() {
        return root;
    }

    public Scene getEscena() {
        return escena;
    }

    public Canvas getLienzo() {
        return lienzo;
    }

    public HashMap<String, Image> getImagenes() {
        return imagenes;
    }
}

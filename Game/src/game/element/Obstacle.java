package game.element;

import game.Constantes;
import game.visiteur.Joueur;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe pour les éléments qui vont être visités
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public abstract class Obstacle extends ImageView implements Element {

    private final Image image;  // Image de l'obstacle
    private final List<Joueur> joueursVisite;   // Liste des joueurs visiteurs
    
    /**
     * Constructeur de la classe Obstacle
     * @param imageNomFichier Nom du fichier de l'obstacle
     */
    public Obstacle(String imageNomFichier) {
        image = new Image(getClass().getResource(imageNomFichier).toString(),
                Constantes.CELL_SIZE, Constantes.CELL_SIZE, true, true);
        joueursVisite = new ArrayList<>();
        nouvelleRandomPosition();
        setImage(image);
    }
    
    /**
     * 
     * @param joueur
     * @return 
     */
    public boolean aEteVisitePar(Joueur joueur) {
        return joueursVisite.contains(joueur);
    }
    
    public void setVisite(Joueur joueur) {
        joueursVisite.add(joueur);
    }
    
    public final void nouvelleRandomPosition() {
        int position = (int) (Math.random() * Constantes.NUM_COLS);
        setX(position * Constantes.CELL_SIZE);
        setY(-(Math.random() * Constantes.GAME_HEIGHT));
    }
    
    public abstract int getModifVie();
    
    public abstract int getPoints();
}

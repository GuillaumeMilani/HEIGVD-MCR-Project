package game.element;

import game.Constantes;
import game.visiteur.Joueur;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Obstacle extends ImageView implements Element {

    private final Image image;
    private final List<Joueur> joueursVisite;
    
    public Obstacle(String imageNomFichier) {
        image = new Image(getClass().getResource(imageNomFichier).toString(),
                Constantes.CELL_SIZE, Constantes.CELL_SIZE, true, true);
        joueursVisite = new ArrayList<>();
        nouvelleRandomPosition();
        setImage(image);
    }
    
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

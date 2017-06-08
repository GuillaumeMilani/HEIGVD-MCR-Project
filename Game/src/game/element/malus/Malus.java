package game.element.malus;

import game.element.Obstacle;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Malus extends Obstacle {
    public Malus(String imageNomFichier) {
        super(imageNomFichier);
    }
}

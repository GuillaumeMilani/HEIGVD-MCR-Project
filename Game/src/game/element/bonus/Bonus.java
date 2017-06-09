package game.element.bonus;

import game.element.Obstacle;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Bonus extends Obstacle {
    public Bonus(String imageNomFichier) {
        super(imageNomFichier);
    }
}

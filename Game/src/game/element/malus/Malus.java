package game.element.malus;

import game.element.Obstacle;
import game.visteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Malus extends Obstacle {

    private final int degats;
    private final int points;

    public Malus(int degats, int points, String imageNomFichier) {
        super(imageNomFichier);
        this.degats = degats;
        this.points = points;
    }

    @Override
    public int getModifVie() {
        return degats;
    }
    
    @Override
    public int getPoints() {
        return points;
    }
    
    @Override
    public void accept(Visiteur v) {
        v.visite(this);
    }
}

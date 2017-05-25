package game.element.obstacle;

import game.element.Element;
import game.visteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Obstacle implements Element {

    private double degat;
    private double facteurRalenti;

    public Obstacle(double degat, double facteurRalenti) {
        this.degat = degat;
        this.facteurRalenti = facteurRalenti;
    }

    public double getDegat() {
        return degat;
    }

    public double getFacteurRalenti() {
        return facteurRalenti;
    }

    @Override
    public void accept(Visiteur v) {
        v.visite(this);
    }
}

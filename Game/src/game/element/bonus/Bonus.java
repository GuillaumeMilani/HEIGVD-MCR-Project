package game.element.bonus;

import game.element.Obstacle;
import game.visteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Bonus extends Obstacle {

    private final int soins;
    private final int points;
    
    public Bonus(int soins, int points, String imageNomFichier) {
        super(imageNomFichier);
        this.soins = soins;
        this.points = points;
    }
    
    @Override
    public int getModifVie() {
        return soins;
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

package game.visteur;

import game.element.bonus.Bonus;
import game.element.obstacle.Obstacle;

/**
 *
 * @author Gabriel Luthier
 */
public interface Visiteur {

    public void visite(Obstacle o);
    
    public void visite(Bonus b);
}

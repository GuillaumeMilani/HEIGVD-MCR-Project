package game.visteur;

import game.element.bonus.Bonus;
import game.element.malus.Malus;

/**
 *
 * @author Gabriel Luthier
 */
public interface Visiteur {

    public void visite(Malus o);
    
    public void visite(Bonus b);
}

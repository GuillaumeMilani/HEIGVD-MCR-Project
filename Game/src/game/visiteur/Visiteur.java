package game.visiteur;

import game.element.Obstacle;
import game.element.bonus.Potion;
import game.element.bonus.Toilette;
import game.element.malus.Flaque;
import game.element.malus.Voiture;

/**
 *
 * @author Gabriel Luthier
 */
public interface Visiteur {

    void visite(Potion p);
    void visite(Toilette t);
    void visite(Flaque f);
    void visite(Voiture v);
}

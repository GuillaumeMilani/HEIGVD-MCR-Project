package game.visiteur;

import game.element.bonus.*;
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
    void visite(Pain p);
    void visite(Salade s);
    void visite(Tomate t);
    void visite(Viande v);
}

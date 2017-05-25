package game.element.malus;

import game.Constantes;

/**
 *
 * @author Gabriel Luthier
 */
public class Flaque extends Malus {

    public Flaque() {
        super(Constantes.Malus.Flaque.degat,
                Constantes.Malus.Flaque.facteurRalenti);
    }
}

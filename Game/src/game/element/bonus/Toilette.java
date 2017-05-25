package game.element.bonus;

import game.Constantes;

/**
 *
 * @author Gabriel Luthier
 */
public class Toilette extends Bonus {

    public Toilette() {
        super(Constantes.Bonus.Toilette.soin,
                Constantes.Bonus.Toilette.facteurAccelere);
    }
}

package game.element.bonus;

import game.Constantes;

/**
 *
 * @author Gabriel Luthier
 */
public class Potion extends Bonus {

    public Potion() {
        super(Constantes.Bonus.Potion.soins,
                Constantes.Bonus.Potion.points,
                Constantes.Bonus.Potion.imageNomFichier);
    }
}

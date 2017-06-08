package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Potion extends Bonus {

    public Potion() {
        super(Constantes.Bonus.Potion.imageNomFichier);
    }
    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Pain extends SandwichElement {

    public Pain() {
        super(Constantes.Bonus.Pain.imageNomFichier);
    }

    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

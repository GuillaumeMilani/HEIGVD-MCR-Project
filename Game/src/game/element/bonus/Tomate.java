package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Tomate extends SandwichElement {

    public Tomate() {
        super(Constantes.Bonus.Tomate.imageNomFichier);
    }

    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

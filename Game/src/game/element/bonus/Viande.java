package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Viande extends SandwichElement {

    public Viande() {
        super(Constantes.Bonus.Viande.imageNomFichier);
    }

    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

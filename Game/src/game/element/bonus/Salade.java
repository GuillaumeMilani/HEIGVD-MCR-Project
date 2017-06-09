package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Salade extends SandwichElement {

    public Salade() {
        super(Constantes.Bonus.Salade.imageNomFichier);
    }

    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

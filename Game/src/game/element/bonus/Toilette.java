package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Toilette extends Bonus {

    public Toilette() {
        super(Constantes.Bonus.Toilette.imageNomFichier);
    }
    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

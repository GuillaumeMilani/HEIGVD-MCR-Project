package game.element.malus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Flaque extends Malus {

    public Flaque() {
        super(Constantes.Malus.Flaque.imageNomFichier);
    }
    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

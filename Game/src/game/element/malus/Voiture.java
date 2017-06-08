package game.element.malus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public class Voiture extends Malus {

    public Voiture() {
        super(Constantes.Malus.Voiture.imageNomFichier);
    }
    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

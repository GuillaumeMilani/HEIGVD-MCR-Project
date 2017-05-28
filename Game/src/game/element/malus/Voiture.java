package game.element.malus;

import game.Constantes;

/**
 *
 * @author Gabriel Luthier
 */
public class Voiture extends Malus {

    public Voiture() {
        super(Constantes.Malus.Voiture.degats,
                Constantes.Malus.Voiture.points,
                Constantes.Malus.Voiture.imageNomFichier);
    }
}

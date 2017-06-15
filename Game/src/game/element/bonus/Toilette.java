package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 * Classe pour représenter l'élément bonus Toilette
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Toilette extends Bonus {

    /**
     * Constructeur de la classe Toilette
     */
    public Toilette() {
        super(Constantes.Bonus.Toilette.imageChemin);
    }

    /**
     * Surcharge de la méthode 'accepte' pour accepter un visiteur
     *
     * @param v Visiteur accepté par l'élement
     */
    @Override
    public void accepte(Visiteur v) {
        visiteurVisite.add(v);
        v.visite(this);
    }
}

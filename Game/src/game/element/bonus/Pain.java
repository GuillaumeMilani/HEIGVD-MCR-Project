package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 * Classe pour représenter l'élément bonus Pain
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Pain extends SandwichElement {

    /**
     * Constructeur de la classe Pain
     */
    public Pain() {
        super(Constantes.Bonus.Pain.imageChemin);
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

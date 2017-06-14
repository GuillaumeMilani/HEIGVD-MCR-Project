package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 * Classe pour représenter l'élément bonus Potion
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public class Potion extends Bonus {

    /**
     * Constructeur de la classe Potion
     */
    public Potion() {
        super(Constantes.Bonus.Potion.imageNomFichier);
    }

    /**
     * Surcharge de la méthode 'accepte' pour accepter un visiteur
     * @param v Visiteur accepté par l'élement
     */
    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

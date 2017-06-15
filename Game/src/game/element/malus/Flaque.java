package game.element.malus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Flaque extends Malus {

    /**
     * Constructeur de la classe Flaque
     */
    public Flaque() {
        super(Constantes.Malus.Flaque.imageNomFichier);
    }

    /**
     * Surcharge de la méthode accepte de la classe Element pour accepter un visiteur
     *
     * @param v Le visiteur qui va réaliser une visite sur l'élément Flaque
     */
    @Override
    public void accepte(Visiteur v) {
        visiteurVisite.add(v);
        v.visite(this);
    }
}

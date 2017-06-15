package game.element.malus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Voiture extends Malus {

    /**
     * Constructeur de la classe Voiture
     */
    public Voiture() {
        super(Constantes.Malus.Voiture.imageNomFichier);
    }

    /**
     * Surcharge de la méthode accepte de la classe Element pour accepter un visiteur
     *
     * @param v Le visiteur qui va réaliser une visite sur l'élément Voiture
     */
    @Override
    public void accepte(Visiteur v) {
        v.visite(this);
    }
}

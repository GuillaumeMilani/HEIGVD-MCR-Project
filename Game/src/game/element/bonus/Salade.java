package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

/**
 * Classe pour représenter l'élément bonus Salade
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public class Salade extends SandwichElement {

    /**
     * Constructeur de la classe Salade
     */
    public Salade() {
        super(Constantes.Bonus.Salade.imageNomFichier);
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

package game.element.bonus;

import game.element.Obstacle;

/**
 * Classe abstraite pour définir l'obstacle Bonus. Cet obstacle est une bonification 
 * pour les joueurs qui font une visite dessus.
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public abstract class Bonus extends Obstacle {
    
    /**
     * Constructeur de la classe Bonus
     * @param imageNomFichier Chemin vers l'image du fichier
     */
    public Bonus(String imageNomFichier) {
        super(imageNomFichier);
    }
}

package game.element.malus;

import game.element.Obstacle;

/**
 * Classe abstraite pour définir l'obstacle Malus. Cet obstacle est une pénalisation 
 * pour les joueurs qui font une visite dessus.
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public abstract class Malus extends Obstacle {
    
    /**
     * Constructeur de la classe Malus
     * @param imageNomFichier Chemin vers l'image du fichier
     */
    public Malus(String imageNomFichier) {
        super(imageNomFichier);
    }
}

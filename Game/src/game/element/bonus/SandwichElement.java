package game.element.bonus;

/**
 * Classe abstraite pour représenter l'élément bonus SandwichElement
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public abstract class SandwichElement extends Bonus {

    /**
     * Constructeur de la classe SandwichElement
     * @param imageNomFichier Chemin vers l'image d'un élément d'un Sandwich
     */
    public SandwichElement(String imageNomFichier) {
        super(imageNomFichier);
    }
}

package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe pour représenter l'élément bonus Sandwich
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public class Sandwich extends Bonus {

    private List<SandwichElement> ingredients;  // Liste d'ingredients dans le Sandwich

    /**
     * Constructeur de la classe Sandwich
     */
    public Sandwich() {
        super(Constantes.Bonus.Sandwich.imageNomFichier);
        
        ingredients = new LinkedList<>();
        ingredients.add(new Pain());
        ingredients.add(new Salade());
        ingredients.add(new Tomate());
        ingredients.add(new Viande());
    }

    /**
     * Surcharge de la méthode 'accepte' pour accepter un visiteur
     * @param v Visiteur accepté par l'élement
     */
    @Override
    public void accepte(Visiteur v) {
        ingredients.forEach(i -> i.accepte(v));
    }
}

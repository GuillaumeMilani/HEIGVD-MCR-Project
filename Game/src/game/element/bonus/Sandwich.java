package game.element.bonus;

import game.Constantes;
import game.visiteur.Visiteur;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gabriel Luthier
 */
public class Sandwich extends Bonus {

    private List<SandwichElement> ingredients;

    public Sandwich() {
        super(Constantes.Bonus.Sandwich.imageNomFichier);
        
        ingredients = new LinkedList<>();
        ingredients.add(new Pain());
        ingredients.add(new Salade());
        ingredients.add(new Tomate());
        ingredients.add(new Viande());
    }

    public void accepte(Visiteur v) {
        ingredients.forEach(i -> i.accepte(v));
    }
}

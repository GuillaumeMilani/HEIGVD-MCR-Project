package game.element.bonus;

import game.Constantes;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gabriel Luthier
 */
public class Sandwich extends Bonus {

    List<SandwichElement> ingredients;

    public Sandwich() {
        super(Constantes.Bonus.Sandwich.soins,
                Constantes.Bonus.Sandwich.points,
                Constantes.Bonus.Sandwich.imageNomFichier);
        
        ingredients = new LinkedList<>();
        ingredients.add(new Pain());
        ingredients.add(new Salade());
        ingredients.add(new Tomate());
        ingredients.add(new Viande());
    }

    @Override
    public int getModifVie() {
        return ingredients.stream()
                .reduce(0,
                        (acc, i) -> acc += i.getModifVie(),
                        (a, b) -> a + b);
    }

    @Override
    public int getPoints() {
        return ingredients.stream()
                .reduce(0,
                        (acc, i) -> acc += i.getPoints(),
                        (a, b) -> a + b);
    }
}

package game.element;

import game.visteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public interface Element {

    public void accept(Visiteur v);
}

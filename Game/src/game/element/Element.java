package game.element;

import game.visiteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public interface Element {

    public void accept(Visiteur v);
}

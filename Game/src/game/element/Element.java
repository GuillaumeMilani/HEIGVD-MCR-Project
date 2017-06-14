package game.element;

import game.visiteur.Visiteur;

/**
 * Interface pour les Élements du pattern Visitor, qui contient la méthode accept
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public interface Element {

    /**
     * Méthode du pattern Vistor utilisé par chaque élément (obstacle dans notre cas) qui vont apparaitre au fur et à
     * mesure dans le jeu
     *
     * @param v Visiteur qui va faire la visite de l'objet courant
     */
    public void accepte(Visiteur v);
}

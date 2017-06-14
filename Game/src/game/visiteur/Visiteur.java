package game.visiteur;

import game.element.bonus.*;
import game.element.malus.Flaque;
import game.element.malus.Voiture;

/**
 * Interface pour le Visiteur qui peut réaliser une visite sur chaque élément du jeu
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public interface Visiteur {

    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Potion
     * @param p L'élément Potion à visiter
     */
    void visite(Potion p);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Toilette
     * @param t L'élément Toilette à visiter
     */
    void visite(Toilette t);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Flaque
     * @param f L'élément Flaque à visiter
     */
    void visite(Flaque f);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Voiture
     * @param v L'élément Voiture à visiter
     */
    void visite(Voiture v);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Pain
     * @param p L'élément Pain à visiter
     */
    void visite(Pain p);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Salade
     * @param s L'élément Salade à visiter
     */
    void visite(Salade s);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Tomate
     * @param t L'élément Tomate à visiter
     */
    void visite(Tomate t);
    
    /**
     * Méthode pour pouvoir réaliser une visite sur les éléments Viande
     * @param v L'élément Viande à visiter
     */
    void visite(Viande v);
}

package game.visiteur;

import game.Constantes;
import game.element.bonus.*;
import game.element.malus.Flaque;
import game.element.malus.Voiture;

/**
 * Classe qui représente le joueur Godefroy
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public class Godefroy extends Joueur {

    /**
     * Constructeur de la classe du joueur Godefroy
     */
    public Godefroy() {
        super(Constantes.Joueurs.Godefroy.nom,
                Constantes.Joueurs.Godefroy.maxVie,
                Constantes.Joueurs.Godefroy.facteurVie,
                Constantes.Joueurs.Godefroy.facteurScore,
                Constantes.Joueurs.Godefroy.position,
                Constantes.Joueurs.Godefroy.imageNomFichier);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur une Potion
     * @param p L'élément Potion à visiter
     */
    @Override
    public void visite(Potion p) {
        modifieVie(10);
        modifieScore(100);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur un Toilette
     * @param t L'élément Toilette à visiter
     */
    @Override
    public void visite(Toilette t) {
        modifieVie(30);
        modifieScore(200);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur une Flaque
     * @param f L'élément Flaque à visiter
     */
    @Override
    public void visite(Flaque f) {
        modifieVie(-10);
        modifieScore(-80);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur une Voiture
     * @param v L'élément Voiture à visiter
     */
    @Override
    public void visite(Voiture v) {
        modifieVie(-40);
        modifieScore(-150);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur du Pain
     * @param p L'élément Pain à visiter
     */

    @Override
    public void visite(Pain p) {
        modifieVie(1);
        modifieScore(10);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur une Salade
     * @param s L'élément Salade à visiter
     */
    @Override
    public void visite(Salade s) {
        modifieVie(2);
        modifieScore(20);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur une Tomate
     * @param t L'élément Tomate à visiter
     */
    @Override
    public void visite(Tomate t) {
        modifieVie(3);
        modifieScore(30);
    }

    /**
     * Surcharge de la méthode visite pour une visite sur de la Viande
     * @param v L'élément Viande à visiter
     */
    @Override
    public void visite(Viande v) {
        modifieVie(4);
        modifieScore(40);
    }
}

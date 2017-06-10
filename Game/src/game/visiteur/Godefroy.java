package game.visiteur;

import game.Constantes;
import game.element.bonus.*;
import game.element.malus.Flaque;
import game.element.malus.Voiture;

/**
 *
 * @author Gabriel Luthier
 */
public class Godefroy extends Joueur {

    public Godefroy() {
        super(Constantes.Joueurs.Godefroy.nom,
                Constantes.Joueurs.Godefroy.maxVie,
                Constantes.Joueurs.Godefroy.facteurVie,
                Constantes.Joueurs.Godefroy.facteurScore,
                Constantes.Joueurs.Godefroy.position,
                Constantes.Joueurs.Godefroy.imageNomFichier);
    }

    @Override
    public void visite(Potion p) {
        modifieVie(10);
        modifieScore(100);
    }

    @Override
    public void visite(Toilette t) {
        modifieVie(30);
        modifieScore(200);
    }

    @Override
    public void visite(Flaque f) {
        modifieVie(-10);
        modifieScore(-80);
    }

    @Override
    public void visite(Voiture v) {
        modifieVie(-40);
        modifieScore(-150);
    }

    @Override
    public void visite(Pain p) {
        modifieVie(1);
        modifieScore(10);
    }

    @Override
    public void visite(Salade p) {
        modifieVie(2);
        modifieScore(20);
    }

    @Override
    public void visite(Tomate p) {
        modifieVie(3);
        modifieScore(30);
    }

    @Override
    public void visite(Viande p) {
        modifieVie(4);
        modifieScore(40);
    }
}

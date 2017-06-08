package game.visiteur;

import game.Constantes;
import game.element.bonus.Potion;
import game.element.bonus.Toilette;
import game.element.malus.Flaque;
import game.element.malus.Voiture;

/**
 *
 * @author Gabriel Luthier
 */
public class Jacquouille extends Joueur {

    public Jacquouille() {
        super(Constantes.Joueurs.Jacquouille.nom,
                Constantes.Joueurs.Jacquouille.maxVie,
                Constantes.Joueurs.Jacquouille.facteurVie,
                Constantes.Joueurs.Jacquouille.facteurScore,
                Constantes.Joueurs.Jacquouille.position,
                Constantes.Joueurs.Jacquouille.imageNomFichier);
    }

    public void visite(Potion p) {
        modifieVie(10);
        modifieScore(100);
    }

    public void visite(Toilette t) {
        modifieVie(30);
        modifieScore(200);
    }

    public void visite(Flaque f) {
        modifieVie(-10);
        modifieScore(-80);
    }

    public void visite(Voiture v) {
        modifieVie(-40);
        modifieScore(-150);
    }
}
